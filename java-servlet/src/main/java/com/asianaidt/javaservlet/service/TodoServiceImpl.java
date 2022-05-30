package com.asianaidt.javaservlet.service;

import com.asianaidt.javaservlet.domain.Todo;
import com.asianaidt.javaservlet.repository.TodoRepository;

import java.util.List;
import java.util.Optional;

public class TodoServiceImpl implements  TodoService {
    TodoRepository todoRepository = new TodoRepository();

    @Override
    public List<Todo> findAll() {
        return todoRepository.findAll();
    }

    @Override
    public List<Todo> getTodoByUsername(final String username) {
        return todoRepository.findByUsername(username);
    }

    @Override
    public Optional<Todo> getTodoById(final long id) {
        return todoRepository.findById(id);
    }

    @Override
    public Todo saveTodo(Todo todo) {
        todoRepository.save(todo);
        return todo;
    }
}
