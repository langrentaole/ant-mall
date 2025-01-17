package com.langrentao.antmall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.langrentao.antmall.business.*.mapper")
@SpringBootApplication
public class AntMallApplication {

    public static void main(String[] args) {
        SpringApplication.run(AntMallApplication.class, args);
    }

}
