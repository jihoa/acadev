package com.asianaidt.springmvc.step02.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RootConfig {
    // modelmapper 등록
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
