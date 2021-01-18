package com.jimwang;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author striverwang
 */
@Slf4j
@SpringBootApplication
@MapperScan(basePackages = {"com.jimwang"})
public class JimblogApplication {
    public static void main(String[] args) {
        SpringApplication.run(JimblogApplication.class, args);
    }
}
