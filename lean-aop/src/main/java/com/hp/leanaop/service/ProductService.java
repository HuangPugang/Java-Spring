package com.hp.leanaop.service;

import com.hp.leanaop.AdminOnly;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Paul on 2018/8/3
 */
@Service
public class ProductService {

    @Autowired
    AuthService authService;

    @AdminOnly
    public void delete() {

//        authService.checkAccess();
    }

    public void insert() {

        authService.checkAccess();
    }
}
