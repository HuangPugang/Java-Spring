package com.hp.multidata.aop;

import com.hp.multidata.config.DSThreadLocal;
import com.hp.multidata.config.DSType;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.PriorityOrdered;
import org.springframework.stereotype.Component;


/**
 * Created by Paul on 2018/8/11
 */
@Aspect
@Component
public class DSAnnotationAspect implements PriorityOrdered {

    private static Logger log = LoggerFactory.getLogger(DSAnnotationAspect.class);


    @Pointcut("@annotation(com.hp.multidata.annotation.DSRead) ")
    public void chooseReadType() {

    }

    @Pointcut("@annotation(com.hp.multidata.annotation.DSWrite) ")
    public void chooseWriteType() {

    }

    @Before("chooseReadType()")
    public void setReadDataSourceType() {
        //如果已经开启写事务了，那之后的所有读都从写库读
        if (!DSType.write.getType().equals(DSThreadLocal.getCurrentType())) {
            System.err.println("设置 读ReadSource");
            DSThreadLocal.setRead();
        }

    }

    @Before("chooseWriteType()")
    public void setWriteDataSourceType() {
        System.err.println("设置写 数据连接池");
        DSThreadLocal.setWrite();
    }

    @Override
    public int getOrder() {

        return 2;
    }

}
