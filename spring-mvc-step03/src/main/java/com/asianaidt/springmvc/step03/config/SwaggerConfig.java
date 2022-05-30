package com.asianaidt.springmvc.step03.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * <pre>
 * com.asianaidt.springmvc.step03.config
 * L SwaggerConfig.java
 * RestApi 작성 시 제공 서비스에 대한 명세화 필요시 SWAGGER를 이용하여 작성
 * SWAGGER 사용을 위한 간단 설정 예
 *
 * @date : 2021-05-24 오전 11:22
 * @author : YHKIM
 **/

@Configuration
public class SwaggerConfig {
    /**
     * <pre>
     * apiInfo() : Swagger API 문서에 대한 설명을 표기(선택사항)
     * apis() : Swagger API 문서로 만들기 원하는 basePackage 결로(필수사항)
     *          RequestHandlerSelectors.any()일 경우 모든 패키지
     *          RequestHandlerSelectors.basePackage("com.asianaidt.springmvc.todo.web") : ~todo.web 이하 패키지
     * path() : URL 경로 지정하여 해당 URL에 해당하는 요청에 대해서만 Swagger API 문서로 만듬(필수사항)
     * 그외 consume(), produces()를 주어 Request, Response 의 Content-Type을 설정할 수 있다.
     * </pre>
     *
     **/
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                //.apis(RequestHandlerSelectors.basePackage("com.asianaidt.springmvc.step03.todo"))
                .paths(PathSelectors.any())
                //.paths(PathSelectors.ant("/todo*/**"))
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("기술리더교육")
                .version("1.0.0")
                .description("기술리더 교육 자료")
                .license("Asianadit License")
                .build();
    }


}
