package com.lemon.spring_first;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.lemon.spring_first.mapper")
public class SpringFirstApplication {

    public static void main(String[] args) {

        SpringApplication.run(SpringFirstApplication.class, args);
    }

}
