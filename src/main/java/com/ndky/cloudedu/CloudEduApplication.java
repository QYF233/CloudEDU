package com.ndky.cloudedu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan("com.ndky.cloudedu.mapper")
@SpringBootApplication
public class CloudEduApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudEduApplication.class, args);
    }

}
