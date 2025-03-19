package com.xiaopinyun;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.xiaopinyun.mapper")
public class RecruitApp {
    public static void main(String[] args) {
        SpringApplication.run(RecruitApp.class,args);
    }
}
