package com.hp.multidatatest.controller;

import com.hp.multidatatest.service.MixService;
import com.hp.multidatatest.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Paul on 2018/8/14
 */
@RequestMapping("/test")
@RestController
public class TestContrlller {

    @Autowired
    UsersService usersService;

    @GetMapping("/get")
    public String getName() {

        return usersService.selectByPrimaryKey(1).getUsername();
    }

    @GetMapping("/list")
    public String listByPrimaryKey() {

        return usersService.listByPrimaryKey(1).getUsername();
    }

    @Autowired
    MixService mixService;
    @GetMapping("/insert")
    public String insert() {

        return mixService.insertAndSelect();
    }

}
