package com.asianaidt.springmvc.step01.service;

import com.asianaidt.springmvc.step01.domain.Todo;
import com.asianaidt.springmvc.step01.repository.TodoRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("TodoService 클래스 테스트")
@ExtendWith({SpringExtension.class})
@ContextConfiguration(locations = {"classpath:META-INF/config/step01-servlet.xml"})
class TodoServiceImplTest {
    @Autowired TodoRepository todoRepository;
    @Autowired TodoService todoService ;

    @BeforeEach
    void setup() {
        //todoRepository = new TodoRepository();
        //todoService = new TodoServiceImpl(todoRepository);
        Todo todo = new Todo();
        todo.setId(0);
        todo.setUsername("tester1");
        todo.setDescription("test");
        todo.setTargetDate(new Date());
        todoService.saveTodo(todo);
    }

    @AfterEach
    void clean() {
        todoRepository.clearData();
    }

    @Test
    @DisplayName("Todo 서비스 테스트")
    void todoFindAllTest() {
        // given

        //when
        List<Todo> todos = todoService.findAll();

        // then
        assertThat(todos.size()).isEqualTo(3);
        assertThat(todos.get(2).getUsername()).isEqualTo("tester1");

    }

}