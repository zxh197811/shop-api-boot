package com.fh;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
@SpringBootApplication
//@EnableTransactionManagement
@MapperScan("com.fh.mapper")
public class ShopApiBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopApiBootApplication.class, args);
    }

}
