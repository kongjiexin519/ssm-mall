package com.spring.ssmmall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@MapperScan(basePackages = "com.spring.ssmmall.model.dao")
@EnableSwagger2
@EnableScheduling
public class SsmMallApplication {

    public static void main(String[] args) {
        SpringApplication.run(SsmMallApplication.class, args);
    }

}
