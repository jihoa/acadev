package com.asianaidt.springmvc.step03.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * <pre>
 * com.asianaidt.springmvc.step03.common.exception
 * L ExceptionController.java
 * 예외처리
 * try ~ catch : 정상 동작과 오류 동작이 혼재해 가독성이 떨어지. 사용 지향
 * @ExceptionHandler : @Controller, @RestController가 적용된 Bean내에서 발생하는 예외를 처리
 * @ControllerAdvice : 모든 @Controller 즉, 전역에서 발생하는 예외를 잡아 처리리
*
 * @date : 2021-05-24 오후 6:01
 * @author : YHKIM
 **/
@Slf4j
@ControllerAdvice
public class ExceptionController {
    // 400
    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<Object>  badRequestException(final RuntimeException ex) {
        log.warn("error : {}", ex);
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    // 500
    @ExceptionHandler({ Exception.class })
    public ResponseEntity<Object> handleAll(final Exception ex) {
        log.info(ex.getClass().getName());
        log.error("error : {}", ex);
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
