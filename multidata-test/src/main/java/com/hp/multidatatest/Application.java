package com.hp.multidatatest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ComponentScan(basePackages = {"com.hp"})
@MapperScan(basePackages = "com.hp.multidatatest.data.mappers")
@EnableTransactionManagement(order = 10)
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
