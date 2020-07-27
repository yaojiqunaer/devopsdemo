package com.trs.devopsdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.trs.devopsdemo.domain.mapper")
@SpringBootApplication
public class DevopsdemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DevopsdemoApplication.class, args);
    }

}
