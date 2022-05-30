package com.asianaidt.springrestful.step01.config;

import com.asianaidt.springrestful.step01.resolver.AccessUserArgumentResolver;
import com.asianaidt.springrestful.step01.resolver.UserAgentArgumentResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Autowired
    private UserAgentArgumentResolver userAgentArgumentResolver;
    @Autowired
    private AccessUserArgumentResolver accessUserArgumentResolver;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(userAgentArgumentResolver);
        resolvers.add(accessUserArgumentResolver);
    }
}
