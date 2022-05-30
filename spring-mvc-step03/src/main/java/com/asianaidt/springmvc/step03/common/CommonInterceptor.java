package com.asianaidt.springmvc.step03.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <pre>
 * com.asianaidt.springmvc.step03.common
 * L CommonInterceptor.java
 *
 * 인터셉터는 디스패처 서블리에서 컨트롤러 전(preHandle)후(postHandle)에서 어떤 제어가 필요할시 사용한다.
 * springframework 5.3 부터 HandlerInterceptorAdapter는 deprecated 되어 extends 안됨
 * HandlerInterceptor 인터페이스ㅅ를 구현해야함.
 * 통상 로그인 / 인증 같은 부분 처리 시 사용. 유사품으로 Filter, ArgumentResolver 등이 있음.
 * 최근에는 AOP로 사용함.
 * @date : 2021-05-24 오후 3:47
 * @author : YHKIM
 **/
@Slf4j
public class CommonInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("================ preHandle =========================");
        log.info("특정 URI를 호출 시 컨트롤러 접근전에 실행됨.");
        log.info("Request URI \t: " + request.getRequestURI());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("컨트롤러를 경유한 다음 화면(view)으로 결과를 전달하기 전에 실행.");
        log.info("================ postHandle =========================");

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("모든 로직 실행 후 수행.");
        log.info("================ afterCompletion =========================");
    }
}
