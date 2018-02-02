package com.github.peterchen82.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 项目启动入口类
 *
 * @author peterchen
 */
@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.github.peterchen82.mybatis.mapper")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
