package com.asianaidt.springmvc.step02.todo.web;


import com.asianaidt.springmvc.step02.todo.domain.Todo;
import com.asianaidt.springmvc.step02.todo.service.TodoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/todo")
@Api(value = "TODO API")
public class TodoController {
    final TodoService todoService ;
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    // Multiaction 기능.
    @ApiOperation(value = "사용자조회", notes = "Todo 목록 조회하기")
    @ApiResponses({
            @ApiResponse(code = 200, message = "ok"),
            @ApiResponse(code = 404, message = "Method Not Found!")
    })
    @RequestMapping(produces = "application/json", method = RequestMethod.GET)
    public  List<Todo> getTodoAll() {
        List<Todo> todoList = todoService.findAll();

        return todoList ;
    }

    // ant 패턴을 이용한 매칭. 문자 +'-'+ 숫자
    @GetMapping("/{username:[\\w]+[-][\\d]+}")
    public ModelAndView getTodoByUsername(@PathVariable(name = "username", required = false) final String username) {
        System.out.println("getTodoByUsername start");
        ModelAndView modelAndView = new ModelAndView();
        List<Todo> todoList = todoService.getTodoByUsername(username);
        System.out.println(todoList.size() + ": " + username );
        modelAndView.addObject("todoList", todoList);
        modelAndView.setViewName("TodoList");

        return modelAndView;
    }

    @GetMapping("/{id:[\\d]+}")
    public ModelAndView getTodoById(@PathVariable(name = "id") final long id) {
        ModelAndView modelAndView = new ModelAndView();
        Optional<Todo> todo = todoService.getTodoById(id);

        modelAndView.addObject("todo", todo.orElseThrow(() -> new NoSuchElementException("Not Found.")) );
        modelAndView.setViewName("Todo");

        return modelAndView;
    }


}
