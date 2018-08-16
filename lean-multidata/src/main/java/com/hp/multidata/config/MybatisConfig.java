package com.hp.multidata.config;

import com.hp.multidata.utils.SpringContext;
import org.apache.ibatis.session.SqlSessionFactory;
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

import javax.sql.DataSource;
import java.io.IOException;
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


    @Bean(name = "roundRobinDataSourceProxy")
    public AbstractRoutingDataSource roundRobinDataSourceProxy() {

        Map<Object, Object> targetDataSources = new HashMap<Object, Object>();

        targetDataSources.put(DSType.write.getType(), dp.getWriteSource());
        List<DataSource> readlist = dp.getReadSourceList();

        if (readlist == null && readlist.size() == 0) {
            throw new RuntimeException("请配置读数据库");
        }
        for (int i = 0; i < readlist.size(); i++) {
            System.err.println("targetDataSources=" + DSType.read.getType() + i);
            targetDataSources.put(DSType.read.getType() + i, readlist.get(i));
        }
        final int readSize = readlist.size();

        //路由类，寻找对应的数据源
        AbstractRoutingDataSource proxy = new RoutingDS(readSize);

        proxy.setDefaultTargetDataSource(dp.getWriteSource());//默认库
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
