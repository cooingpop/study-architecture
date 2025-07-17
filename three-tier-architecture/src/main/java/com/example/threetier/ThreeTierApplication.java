package com.example.threetier;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.threetier.dao")
public class ThreeTierApplication {

    public static void main(String[] args) {
        SpringApplication.run(ThreeTierApplication.class, args);
    }
}
