package com.asianaidt.springmvc.step02.init;

import com.asianaidt.springmvc.step02.config.RootConfig;
import com.asianaidt.springmvc.step02.config.SwaggerConfig;
import com.asianaidt.springmvc.step02.config.WebMvcConfig;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@WebAppConfiguration
@ExtendWith({SpringExtension.class})
@ContextConfiguration(classes = { WebMvcConfig.class, RootConfig.class, SwaggerConfig.class })
class Step02WebAppInitializerTest {
    @Autowired
    ApplicationContext context;

    @DisplayName("Web Application Loading Test : 설정 확인용")
    @Test
    @Disabled
    void context_loading_test() {

    }

    @Test
    void viewResolver_get_Prefix_test() {
        ModelMapper modelMapper = (ModelMapper) context.getBean("modelMapper");
        assertThat(modelMapper).isNotNull().as("널이면 안됨");
    }

}