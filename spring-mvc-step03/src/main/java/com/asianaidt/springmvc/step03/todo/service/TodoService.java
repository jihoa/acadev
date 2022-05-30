package com.asianaidt.springmvc.step03.todo.service;

import com.asianaidt.springmvc.step03.todo.domain.Todo;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface TodoService {
    List<Todo> getTodosByUser(String user);

    Optional<Todo> getTodoById(long id);

    void updateTodo(Todo todo);

    void addTodo(String name, String desc, Date targtDate, Boolean isDone);

    void deleteTodo(long id);

    void saveTodo(Todo todo);
}
