package com.hp.leanaop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Paul on 2018/8/4
 */
@Aspect
@Configuration
public class ObjAspect {

    //匹配ProductService类里头的所有方法
    @Pointcut("this(com.hp.leanaop.service.ProductService)")
    public void matchType(){

    }
//    //匹配com.hp.leanaop包及子包下所有类的方法
//    @Pointcut("within(com.hp.leanaop..*)")
//    public void matchPackage(){
//
//    }

    @Before("matchType()")
    public void before(){

        System.out.println("");
        System.out.println("ObjAspect");
    }

}
