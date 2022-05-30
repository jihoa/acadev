package com.asianaidt.springrestful.step01.resolver;

import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * <pre>
 * com.asianaidt.springrestful.step01.resolver
 * L AccessUserArgumentResolver.java
 * 컨트롤러에 들어오는 파라미터를 가공(View에서 암호화된 내용 복호화)하거나 바인딩 해주는 역할.
 * 컨트롤러에 중복되는 코드를 없애면서 컨트롤러의 파라미터에 대한 공통기능 제공
 * 특정 클래스나 특정 애너테이션의 요청 파라미터를 수정해야하거나, 클래스의 파라미터를 조작 혹은
 * 공통적으로 사용해야하는 파라미터를 바인딩
 * @date : 2021-06-03 오후 2:41
 * @author : YHKIM
 **/
@Component
public class AccessUserArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return false;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        return null;
    }
}
