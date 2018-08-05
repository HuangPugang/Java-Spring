package com.hp.leanaop.service;

import com.hp.leanaop.CurrentUserHolder;
import org.springframework.stereotype.Service;

/**
 * Created by Paul on 2018/8/3
 */
@Service
public class AuthService {

    public void checkAccess(){

        String user = CurrentUserHolder.get();
        if (!"admin".equals(user)){
            throw new RuntimeException("operation not allow");
        }else {
            System.out.println("校验成功");
        }
    }
}
