package com.xiaopinyun;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.xiaopinyun.mapper")
public class ResumeSubmitApp {
    public static void main(String[] args) {
        SpringApplication.run(ResumeSubmitApp.class,args);
    }
}
