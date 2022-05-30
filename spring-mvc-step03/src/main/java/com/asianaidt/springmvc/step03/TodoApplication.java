package com.asianaidt.springmvc.step03;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

// spring boot + jsp 사용 시 jar 빌드 후 jsp를 못찾는 문제 발생시 war로 빌드해서 조치함.
// Spring boot 를 war로 빌드하기 위해 SpringBootServletInitializer를 상속받아 SpringApplicationBuilder를 구현해야함.
// build.gradle에 plugin 'war' 추가해야함.
@MapperScan(basePackageClasses = TodoApplication.class)
@SpringBootApplication
@EnableAspectJAutoProxy
public class TodoApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(TodoApplication.class);
    }
    public static void main(String[] args) {
        SpringApplication.run(TodoApplication.class,args);
    }
}
