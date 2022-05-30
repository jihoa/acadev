package com.asianaidt.javaservlet.service;

import com.asianaidt.javaservlet.domain.Todo;
import com.asianaidt.javaservlet.repository.TodoRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TodoServiceImplTest {
    TodoService todoService;
    TodoRepository todoRepository;

    @BeforeEach
    void init() {
        todoService = new TodoServiceImpl();
        todoRepository = new TodoRepository();
    }

    @AfterEach
    void clear() {
        todoRepository.clearData();
    }

    @Test
    void testFindAll() {
        // Given
        AtomicLong sequence = new AtomicLong(0);
        List<Todo> dataList = new ArrayList<>();
        Todo data1 = Todo.builder().id(sequence.incrementAndGet()).description("user1에 대한 상세설명").username("user1").targetDate(new Date()).build();
        Todo data2 = Todo.builder().id(sequence.incrementAndGet()).description("user2에 대한 상세 설명").username("user2").targetDate(new Date()).build();
        dataList.add(data1);
        dataList.add(data2);
        todoService.saveTodo(data1);
        todoService.saveTodo(data2);

        // When
        List<Todo> condition = todoService.findAll();

        // Then
        assertEquals(dataList.get(0).getUsername(), condition.get(0).getUsername());

    }

    @Test
    void testSaveTodo() {
        // given
        Todo todo1 = Todo.builder()
                .username("techLeader")
                .description("테스트 계정")
                .targetDate(new Date())
                .build();

        // when
        todoService.saveTodo(todo1);
        List<Todo> result = todoService.getTodoByUsername("techLeader");

        String username = result.stream().filter(a -> a.getUsername().equalsIgnoreCase("techleader")).findFirst().map(Todo::getUsername).orElseThrow(() -> new NoSuchElementException());
        System.out.println("username: "+ username);

        // then
        assertThat(result.stream().filter(todo -> todo.getUsername().equals("techLeader")).findFirst().map(Todo::getUsername).get()).isEqualTo(todo1.getUsername());
    }

    @Test
    void testFindById() {
        // given
        Todo todo1 = Todo.builder()
                .username("techLeader2")
                .description("테스트 계정")
                .targetDate(new Date())
                .build();
        todoService.saveTodo(todo1);

        // when
        List<Todo> todoList = todoService.getTodoByUsername("techLeader2");
        long id = todoList.stream().filter(a -> a.getUsername().equals("techLeader2")).findFirst().map(Todo::getId).orElseThrow(() -> new NoSuchElementException());
        Optional<Todo> result = todoService.getTodoById(id);

        System.out.println(id + ":" + result.get().getId() );
        //then
        assertThat(result.get().getId() == id);
    }

}