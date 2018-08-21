package com.hp.hessian.server;

import org.springframework.stereotype.Service;

/**
 * Created by paul on 16/11/24.
 */
@Service("HelloWorldService")
public class HelloWorldServiceImpl implements HelloWorldService {
    @Override
    public String sayHello(String name) {
        return "Hello World! " + name;
    }
}
