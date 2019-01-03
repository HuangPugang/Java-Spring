package com.hp.hessian.client;

import com.hp.core.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by paul on 16/11/24.
 */
@RestController
public class TestController {

    @Autowired
    HelloWorldService helloWorldService;

    @RequestMapping("/test")
    public String test(Integer age, String name) {

        Person p = new Person();
        p.setAge(age);
        p.setName(name);

        return helloWorldService.sayHello(p);

    }
}
