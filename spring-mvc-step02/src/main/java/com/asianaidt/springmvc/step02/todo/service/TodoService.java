package com.asianaidt.springmvc.step02.todo.service;


import com.asianaidt.springmvc.step02.todo.domain.Todo;

import java.util.List;
import java.util.Optional;

public interface TodoService {
    List<Todo> findAll();

    List<Todo> getTodoByUsername(final String username);

    default Optional<Todo> getTodoById(final long id) {
        return null;
    }

    Todo saveTodo(Todo todo);
}
