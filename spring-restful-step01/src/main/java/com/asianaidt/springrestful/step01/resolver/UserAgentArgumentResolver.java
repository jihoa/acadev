package com.asianaidt.springrestful.step01.resolver;

import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

/**
 * <pre>
 * com.asianaidt.springrestful.step01.resolver
 * L UserAgentArgumentResolver.java
 * 컨트롤러에서 파라미터를 바인딩 해주는 역할.
 * 특정 클래스나 특정 애너테이션의 요청 파라미터를 수정해야하거나, 클래스의 파라미터를 조작 혹은
 * 공통적으로 사용해야하는 파라미터를 바인딩

 * @date : 2021-06-03 오후 2:37
 * @author : YHKIM
 **/
@Component
public class UserAgentArgumentResolver implements HandlerMethodArgumentResolver {

    /**
     * <pre>
     * supportsParameter: 컨트롤러의 파라미터 정보가 resolver가 지원하는 지 확인
     * MethodParameter : 체크할 메소드 파라미터
     * </pre>
     **/
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        //@ClientIP 어노테이션이 붙은 파라미터에 대해 적용
        return parameter.hasParameterAnnotation(ClientIP.class);
    }

    /**
     * <pre>
     * 컨트롤러에 실제 바인딩되는 객게
     * </pre>
     *
     **/
    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();

        String clientIp = request.getHeader("X-Forwarded-For");
        if (StringUtils.isEmpty(clientIp)|| "unknown".equalsIgnoreCase(clientIp)) {
            clientIp = request.getHeader("Proxy-Client-IP");
        }
        if (StringUtils.isEmpty(clientIp) || "unknown".equalsIgnoreCase(clientIp)) {
            clientIp = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StringUtils.isEmpty(clientIp) || "unknown".equalsIgnoreCase(clientIp)) {
            clientIp = request.getHeader("HTTP_CLIENT_IP");
        }
        if (StringUtils.isEmpty(clientIp) || "unknown".equalsIgnoreCase(clientIp)) {
            clientIp = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (StringUtils.isEmpty(clientIp) || "unknown".equalsIgnoreCase(clientIp)) {
            clientIp = request.getRemoteAddr();
        }

        return clientIp;
    }

}
