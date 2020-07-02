package com.echo.mobileweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@tk.mybatis.spring.annotation.MapperScan(basePackages = "com.echo.mobileweb.mapper")
public class MobilewebApplication {

    public static void main(String[] args) {
        SpringApplication.run(MobilewebApplication.class, args);
    }

}
