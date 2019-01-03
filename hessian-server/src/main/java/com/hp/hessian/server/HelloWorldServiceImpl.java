package com.hp.hessian.server;

import com.hp.core.Person;
import org.springframework.stereotype.Service;

/**
 * Created by paul on 16/11/24.
 */
//@Service("HelloWorldService")
@Service
public class HelloWorldServiceImpl implements HelloWorldService {
    @Override
    public String sayHello(Person name) {
        return "Hello World! " + name.toString();
    }
}
