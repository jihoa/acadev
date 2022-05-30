package com.asianaidt.springrestful.step01;


import com.asianaidt.springrestful.step01.entity.Member;
import com.asianaidt.springrestful.step01.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.Connector;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@Slf4j
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class SpringRestfulStep01Application /*extends SpringBootServletInitializer*/ {

    /*@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(SpringRestfulStep01Application.class);
    }*/
    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(SpringRestfulStep01Application.class, args);

        // Spring boot 가 몰래 등록하는 Bean 정보
        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
       log.info("============ Bean Names =============");
        for(String beanName : beanNames) {
            log.info("{}", beanName);
        }
    }

    @Bean
    CommandLineRunner runner(MemberRepository memberRepository) {
        return (String[] args) -> {
            Member member1 = new Member("yhkim1", "12345678", "yhkim@asianaidt.com");
            Member member2 = new Member("yhkim2", "12345678", "yhkim@asianaidt.com");
            memberRepository.save(member1);
            memberRepository.save(member2);

            memberRepository.findAll().forEach(x -> log.info("Name is {} and Email is {}", x.getUsername(), x.getEmail()));

        };
    }

    /**
     * <pre>
     * 스프링부트의 톰캣 내장 서버는 기본적으로 커넥터가 한 개로 설정
     * https 요청을 받는 것과 더불어 http 프로토콜로도 요청을 받기 위해서는 커넥터를 하나 더 만들어야 함.
     * server.http2.enabled 구성 속성을 사용하여 스프링 부트는 HTTP/2 지원 활성화 가능
     * JDK8에서는 위 프로토콜을 기본적으로 지원하지 않음. HTTP/2 를 지원할려면 JDK9 이상 사용할 것.
     * </pre>
     **/
    @Bean
    public ServletWebServerFactory serveltContainer(){
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
        tomcat.addAdditionalTomcatConnectors(createStandardConnector());
        return tomcat;
    }

    private Connector createStandardConnector(){
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        connector.setPort(8080);
        return connector;
    }

}
