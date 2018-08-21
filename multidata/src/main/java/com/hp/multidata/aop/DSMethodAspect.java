package com.hp.multidata.aop;

import com.hp.multidata.config.DSTypeThreadLocal;
import com.hp.multidata.config.DSType;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.PriorityOrdered;
import org.springframework.stereotype.Component;


/**
 * 切面里最先执行
 * service切面
 * Created by Paul on 2018/8/11
 */
@Aspect
@Component
public class DSMethodAspect implements PriorityOrdered {

    private static Logger log = LoggerFactory.getLogger(DSMethodAspect.class);


    @Before("execution(* com.hp.*.service.*.select*(..)) " +
            "|| execution(* com.hp.*.service.*.list*(..))")
    public void setRead() {
        //已经是写状态保持写库
        if (!DSType.write.getType().equals(DSTypeThreadLocal.getCurrentType())) {
            System.err.println("设置读 com.hp.multidata.service..*.*(..)");
            DSTypeThreadLocal.setRead();
        }
    }

    @Before("execution(* com.hp.*.service.*.insert*(..)) " +
            "|| execution(* com.hp.*.service.*.add*(..)) " +
            "|| execution(* com.hp.*.service.*.update*(..)) " +
            "|| execution(* com.hp.*.service.*.delete*(..)) ")
    public void setWriteDataSourceType() {

        System.err.println("设置写 com.hp.multidata.service..*.*(..)");
        DSTypeThreadLocal.setWrite();
    }

    @Override
    public int getOrder() {

        return 1;
    }

}
