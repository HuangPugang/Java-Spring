package com.hp.multidata.aop;

import com.hp.multidata.config.DSThreadLocal;
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
public class DSTransitionalAspect implements PriorityOrdered {

    private static Logger log = LoggerFactory.getLogger(DSTransitionalAspect.class);


    @Pointcut("@annotation(org.springframework.transaction.annotation.Transactional) ")
    public void chooseWriteType() {

    }


    @Before("chooseWriteType()")
    public void setWriteDataSourceType() {
        System.out.println("事务设置写 数据连接池");
        DSThreadLocal.setWrite();
    }

    @Override
    public int getOrder() {

        return 3;
    }

}
