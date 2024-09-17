package org.example.adminboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@MapperScan(basePackages = {"org.example.adminboot.**.mapper"})
@EnableAspectJAutoProxy
public class AdminbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminbootApplication.class, args);
    }

}
