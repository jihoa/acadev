package com.asianaidt.springmvc.step03.config;

import com.asianaidt.springmvc.step03.common.CommonInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * <pre>
 * com.asianaidt.springmvc.step03.config
 * L WebMvcConfig.java
 * spring boot 2.x 부터 WebMvcConfigurerAdapter 는 deprecated.
 *
 * @date : 2021-05-24 오후 3:45
 * @author : YHKIM
 **/
@Configuration
public class WebMvcConfig  implements WebMvcConfigurer {

    @Override
    public void addInterceptors( InterceptorRegistry registry) {
        registry.addInterceptor(new CommonInterceptor())
                .addPathPatterns("/")
                .excludePathPatterns("/login")
                .excludePathPatterns("/webjars/*")
                .excludePathPatterns("/css/*")
                .excludePathPatterns("/js/*");
    }

}
