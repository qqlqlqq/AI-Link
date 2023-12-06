package com.quan.ailink;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@MapperScan("com.quan.ailink.mapper")
@EnableCaching
public class AilinkApplication {

    public static void main(String[] args) {
        SpringApplication.run(AilinkApplication.class, args);
    }

}
