package com.asianaidt.springmvc.step03.todo.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
public class WelcomeController {

    @GetMapping("/")
    public ModelAndView welcomePage(ModelMap model) {
        ModelAndView modelAndView = new ModelAndView();

        log.info("Welcome page");
        model.put("name", "yhkim");
        modelAndView.addObject("model", model);
        modelAndView.setViewName("Welcome");

        return modelAndView;
    }

//    private Object getLoggedUsesName() {
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//
//        if (principal instanceof UserDetails) {
//            return ((UserDetails) principal).getUsername();
//        }
//
//        return principal.toString();
//    }


}
