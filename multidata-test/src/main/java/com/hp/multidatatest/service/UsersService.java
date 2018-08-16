package com.hp.multidatatest.service;

import com.hp.multidata.annotation.DSRead;
import com.hp.multidata.annotation.DSWrite;
import com.hp.multidatatest.data.mappers.UsersMapper;
import com.hp.multidatatest.data.models.Users;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Paul on 2018/8/11
 */
@Service
public class UsersService {

    @Autowired
    UsersMapper usersMapper;


    public Users selectByPrimaryKey(Integer id) {

        Users users = new Users();
        users.setUsername("usertest");
        users.setUserpass("password");

        return usersMapper.selectByPrimaryKey(id);
    }

    public Users listByPrimaryKey(Integer id) {

        Users users = new Users();
        users.setUsername("usertest");
        users.setUserpass("password");

        return usersMapper.selectByPrimaryKey(id);
    }

    @DSWrite
    public Users insertByPrimaryKey(Integer id) {

        Users users = new Users();
        users.setUsername("insert");
        users.setUserpass("pinsert");

        usersMapper.insert(users);

        return users;
    }


    @Autowired
    private SqlSessionFactory sqlSessionFactory;

//
//    private Users select() {
//        try (SqlSession session = sqlSessionFactory.openSession()) {
//            UsersMapper mapper = session.getMapper(UsersMapper.class);
//
//
//
//            return mapper.selectByPrimaryKey(1);
//        }
//
//    }

}
