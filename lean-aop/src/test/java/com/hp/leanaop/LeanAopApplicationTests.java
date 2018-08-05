package com.hp.leanaop;

import com.hp.leanaop.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LeanAopApplicationTests {

    @Autowired
    ProductService productService;
    @Test
    public void contextLoads() {
        CurrentUserHolder.set("admin");
        productService.delete();
    }

}
