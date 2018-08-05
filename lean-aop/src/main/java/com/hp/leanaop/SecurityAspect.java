package com.hp.leanaop;

import com.hp.leanaop.service.AuthService;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Paul on 2018/8/3
 */
@Aspect
@Configuration
public class SecurityAspect {


    @Autowired
    private AuthService authService;

    @Pointcut("@annotation(AdminOnly)")
    public void adminOnly() {

    }



    @Before("adminOnly()")
    public void check() {
        authService.checkAccess();

    }
}
