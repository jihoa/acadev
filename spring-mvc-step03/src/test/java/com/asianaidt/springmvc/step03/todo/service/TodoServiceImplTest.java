package com.asianaidt.springmvc.step03.todo.service;

import com.asianaidt.springmvc.step03.todo.domain.Todo;
import com.asianaidt.springmvc.step03.todo.repository.TodoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
class TodoServiceImplTest {
    @Autowired
    TodoService todoService;
    @Autowired  TodoRepository todoRepository;

    @Test
    void addTodo() {
        // Given
        Todo todo = new Todo();
        todo.setId(0L);
        todo.setUsername("yhkim");
        todo.setDescription("todo test description");
        todo.setTargetDate(Date.valueOf(LocalDate.now()));

        // when
        System.out.println(todo.getUsername());
        todoRepository.save(todo);

        // Then
        Todo findTodo = todoRepository.findByUsername("yhkim").get(0);
        assertEquals(todo.getUsername(), findTodo.getUsername() );
    }

}