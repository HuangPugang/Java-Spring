package com.hp.multidata;

import com.hp.multidata.config.DSProperties;
import com.hp.multidata.data.models.Users;
import com.hp.multidata.service.UsersService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

    @Test
    public void contextLoads() {
    }


    @Autowired
    private UsersService usersMapper;

    @Test
    public void test() {


        Users u = usersMapper.selectByPrimaryKey(1);
        System.out.println(u.getUsername());
    }

    @Autowired
    private DSProperties properties;
    @Test
    public void test01() {

        System.out.println(properties.getReads().get(0).getUsername());

    }
}
