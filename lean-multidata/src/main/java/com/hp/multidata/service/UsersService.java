package com.hp.multidata.service;

import com.hp.multidata.annotation.DSRead;
import com.hp.multidata.annotation.DSWrite;
import com.hp.multidata.data.mappers.UsersMapper;
import com.hp.multidata.data.models.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Paul on 2018/8/11
 */
@Service
public class UsersService {

    @Autowired
    UsersMapper usersMapper;


    @DSRead
    public Users selectByPrimaryKey(Integer id) {

        Users users= new Users();
        users.setUsername("usertest");
        users.setUserpass("password");

        return usersMapper.selectByPrimaryKey(id);
    }

    public Users listByPrimaryKey(Integer id) {

        Users users= new Users();
        users.setUsername("usertest");
        users.setUserpass("password");

        return usersMapper.selectByPrimaryKey(id);
    }

    @DSWrite
    public Users insertByPrimaryKey(Integer id) {

        Users users= new Users();
        users.setUsername("insert");
        users.setUserpass("pinsert");

        usersMapper.insert(users);

        return users;
    }




}
