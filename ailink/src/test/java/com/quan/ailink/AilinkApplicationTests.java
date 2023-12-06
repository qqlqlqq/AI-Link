package com.quan.ailink;

import com.quan.ailink.utils.JWTUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AilinkApplicationTests {

    @Test
    void contextLoads() {
        System.out.println(JWTUtil.sign("admin", "dcbf0f7fbd8d9c01c0149dbf69e22beb"));
        boolean verify = JWTUtil.verify("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE3MDE2ODkzNDgsInVzZXJuYW1lIjoiYWRtaW4ifQ.rJGZxO1e8ZEHhZLYVkSKnB1vcUAfcrFfOxSOmhvbAxc", "admin", "dcbf0f7fbd8d9c01c0149dbf69e22beb");
        System.out.println(verify);
    }



}
