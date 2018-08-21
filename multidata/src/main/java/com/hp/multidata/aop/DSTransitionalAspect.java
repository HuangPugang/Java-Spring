package com.hp.multidata.aop;

import com.hp.multidata.config.DSTypeThreadLocal;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.PriorityOrdered;
import org.springframework.stereotype.Component;


/**
 *
 * 切面最后执行
 * 事务注解切面
 * {@link org.springframework.transaction.annotation.Transactional}
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
        DSTypeThreadLocal.setWrite();
    }

    @Override
    public int getOrder() {

        return 3;
    }

}
