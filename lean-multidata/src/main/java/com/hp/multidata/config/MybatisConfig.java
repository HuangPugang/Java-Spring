package com.hp.multidata.config;

import com.hp.multidata.utils.SpringContext;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.tomcat.jdbc.pool.PoolProperties;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.annotation.PreDestroy;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Paul on 2018/8/11
 */
@Configuration
@AutoConfigureAfter(DSProperties.class)
public class MybatisConfig {

    private static Logger log = LoggerFactory.getLogger(MybatisConfig.class);
    @Autowired
    DSProperties dp;

    private DataSource writeSource;

    private List<DataSource> readSourceList;


    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactorys() throws Exception {
        try {
            SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
            sessionFactoryBean.setDataSource(roundRobinDataSourceProxy());

            //设置mapper.xml文件所在位置
            Resource[] resources = new PathMatchingResourcePatternResolver().getResources(dp.getMapperLocations());
            sessionFactoryBean.setMapperLocations(resources);
            return sessionFactoryBean.getObject();
        } catch (IOException e) {
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    private void initWriteDataSource() {
        if (dp.getWrite() == null) {
            throw new RuntimeException("请先配置写数据库");
        }
        System.err.println("初始化写数据源");
        PoolProperties p = DBHelper.buildPoolProperties(dp.getWrite());
        p.setLogAbandoned(true);
        p.setDefaultAutoCommit(true);
        writeSource = new org.apache.tomcat.jdbc.pool.DataSource(p) {
            @PreDestroy
            public void close() {
                super.close(true);
            }
        };
    }

    private void initReadDataSource() {
        readSourceList = new ArrayList<>();
        if (dp.getReads() == null || dp.getReads().size() == 0) {
            throw new RuntimeException("请先配置读数据库");
        }
        System.err.println("初始化读数据源");
        for (int i = 0; i < dp.getReads().size(); i++) {
            PoolProperties p = DBHelper.buildPoolProperties(dp.getReads().get(i));
            p.setLogAbandoned(true);
            p.setDefaultAutoCommit(true);
            readSourceList.add(new org.apache.tomcat.jdbc.pool.DataSource(p) {
                @PreDestroy
                public void close() {
                    super.close(true);
                }
            });
        }
    }


    @Bean(name = "roundRobinDataSourceProxy")
    public AbstractRoutingDataSource roundRobinDataSourceProxy() {

        System.err.println("roundRobinDataSourceProxy");

        initReadDataSource();

        initWriteDataSource();

        Map<Object, Object> targetDataSources = new HashMap<Object, Object>();

        targetDataSources.put(DSType.write.getType(), writeSource);

        if (readSourceList == null && readSourceList.size() == 0) {
            throw new RuntimeException("请配置读数据库");
        }
        for (int i = 0; i < readSourceList.size(); i++) {
            System.err.println("targetDataSources=" + DSType.read.getType() + i);
            targetDataSources.put(DSType.read.getType() + i, readSourceList.get(i));
        }
        final int readSize = readSourceList.size();

        //路由类，寻找对应的数据源
        AbstractRoutingDataSource proxy = new RoutingDS(readSize);

        proxy.setDefaultTargetDataSource(writeSource);//默认库
        proxy.setTargetDataSources(targetDataSources);
        return proxy;
    }


    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    //事务管理
    @Bean
    public PlatformTransactionManager annotationDriveTransactionManager() {
        System.out.println("事务管理");
        return new DataSourceTransactionManager((DataSource) SpringContext.getBean("roundRobinDataSourceProxy"));
    }

}
