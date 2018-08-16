package com.hp.multidatatest.service;

import com.hp.multidatatest.data.models.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Paul on 2018/8/15
 */
@Service
public class MixService {

    @Autowired
    private UsersService usersService;
    @Transactional
    public String insertAndSelect(){
        usersService.insertByPrimaryKey(1);
        Users u = usersService.selectByPrimaryKey(1);

        return u.getUsername();
    }
}
