package com.asianaidt.springmvc.step03.todo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/error")
public class ErrorController {
    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(HttpServletRequest request, Exception ex) {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("exception", ex.getLocalizedMessage());
        modelAndView.addObject("url", request.getRequestURL());
        modelAndView.setViewName("DefaultError");

        return modelAndView;
    }
}
