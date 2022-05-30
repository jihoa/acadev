package com.asianaidt.springmvc.step03;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;

@Slf4j
@Component
public class TestRunner implements ApplicationRunner {
    @Autowired
    DataSource dataSource;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        Connection connection = dataSource.getConnection();
        log.info("url : {}", connection.getMetaData().getURL());
        log.info("username : {}", connection.getMetaData().getUserName());

    }
}
