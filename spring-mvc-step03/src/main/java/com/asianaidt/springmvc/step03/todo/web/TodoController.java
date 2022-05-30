package com.asianaidt.springmvc.step03.todo.web;

import com.asianaidt.springmvc.step03.todo.domain.Todo;
import com.asianaidt.springmvc.step03.todo.dto.RequestDto;
import com.asianaidt.springmvc.step03.todo.service.TodoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Slf4j
@Controller
@Api("TodoCon")
public class TodoController {
    @Autowired
    ModelMapper userMapper;
    // 생성자 DI 이용
    private final TodoService todoService;
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    // Spring Validator 사용 시 @Valid 애너테이션으로 검증이 필요한 객체를 가져오기 전에 수행할 Method 지정
    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(simpleDateFormat, false));
    }

    @InitBinder
    public void initBinderDisallowed(WebDataBinder webDataBinder) {
        webDataBinder.setDisallowedFields("id");
    }

    @ApiOperation(value = "select todoList", notes = "투두 목록 조회")
    @GetMapping(value = "/todoList", produces = "text/html;charset=UTF-8")
    public ModelAndView getTodos(ModelMap model) {
        ModelAndView modelAndView = new ModelAndView("TodoList");
        String name = "admin";
        List<Todo> actualList = todoService.getTodosByUser(name);

        model.put("todoList", actualList);
        modelAndView.addObject("todoList", actualList);
        return modelAndView;
    }
    
    @ApiOperation(value = "REST APT SAMPLE", notes = "API 명세 샘플")
    @GetMapping(value = "/todoListApi", consumes =MediaType.APPLICATION_JSON_VALUE , produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public List<Todo> getTodosApi() {
        String name = "admin";
        List<Todo> actualList = todoService.getTodosByUser(name);
        return  actualList;
        //return ResponseEntity.ok(actualList);
    }

    @ApiOperation(value = "goto todo form", notes = "투두 입력폼 이동")
    @GetMapping(value = "/addTodo", produces = "application/json;charset=UTF-8")
    public ModelAndView addTodoPage(ModelMap model) {
        model.addAttribute("requestDto", new Todo());
        ModelAndView mav = new ModelAndView();
        mav.addObject(model);
        mav.setViewName("Todo");

        return mav;
    }
    @ApiOperation(value = "delete todo", notes = "투두 삭제")
    @GetMapping("/deleteTodo")
    public String deleteTodo(@RequestParam long id) {
        todoService.deleteTodo(id);

        return "redirect:/todoList";
    }

    @ApiOperation(value = "goto update form", notes = "투두 수정 화면")
    @GetMapping("/updateTodo")
    public String showUpdateTodoPage(@RequestParam long id, ModelMap model) {
        Todo todo = todoService.getTodoById(id).get();
//        RequestDto requestDto = new RequestDto();
//        requestDto.setUsername(todo.getUsername() );
//        requestDto.setDescription(todo.getDescription() );
//        requestDto.setTargetDate(todo.getTargetDate() );

        model.put("requestDto", todo.toDto() );

        return "Todo";
    }

    @ApiOperation(value = "update todo", notes = "투두 수정")
    @PostMapping("/updateTodo")
    public String updateTodo(ModelMap model, @Valid RequestDto requestDto, BindingResult result) {
        if (result.hasErrors()) {
            return "Todo";
        }
        requestDto.setUsername(getLoggedInUserName(model));
        // modelmapper를 이용한 DTO -> Model 객체 변환
        Todo todo = userMapper.map(requestDto, Todo.class);
        todoService.updateTodo(todo);

        return "redirect:/todoList";
    }

    @ApiOperation(value = "add todo", notes = "투두 저장")
    @PostMapping("/addTodo")
    public String addTodo(ModelMap model, @Valid RequestDto requestDto, BindingResult result) {

        if (result.hasErrors()) {
            return "Todo";
        }

        requestDto.setUsername(getLoggedInUserName(model));
        //requestDto.setUsername("yhkim");
        todoService.saveTodo(requestDto.toEntity());

        return "redirect:/todoList";
    }

    private String getLoggedInUserName(ModelMap model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        }

        return principal.toString();
    }

}
