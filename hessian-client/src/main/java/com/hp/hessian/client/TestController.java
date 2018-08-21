package com.hp.hessian.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by paul on 16/11/24.
 */
@RestController
public class TestController  {

    @Autowired
    HelloWorldService helloWorldService;
    @RequestMapping("/test")
    public String test(){

        return helloWorldService.sayHello("Spring boot with Hessian.");

    }
}
