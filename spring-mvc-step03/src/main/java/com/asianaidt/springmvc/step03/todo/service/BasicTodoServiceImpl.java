package com.asianaidt.springmvc.step03.todo.service;

import com.asianaidt.springmvc.step03.todo.domain.Todo;
import com.asianaidt.springmvc.step03.todo.repository.TodoRepository;
import com.asianaidt.springmvc.step03.todo.repository.basic.BasicTodoRepository;
import lombok.extern.slf4j.Slf4j;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
public class BasicTodoServiceImpl implements TodoService {
    private final TodoRepository repository = new BasicTodoRepository();

    @Override
    public List<Todo> getTodosByUser(String user) {
        return repository.findByUsername(user);
    }

    @Override
    public Optional<Todo> getTodoById(long id) {
        return repository.findById(id);
    }

    @Override
    public void updateTodo(Todo todo) {
        repository.update(todo);
    }

    @Override
    public void addTodo(String name, String desc, Date targtDate, Boolean isDone) {
        Todo todo = new Todo(name, desc, targtDate);
        repository.save(todo);
    }

    @Override
    public void deleteTodo(long id) {
        Optional<Todo> todo = repository.findById(id);
        if (todo.isPresent()) {
            repository.delete(todo.get());
        }
    }

    @Override
    public void saveTodo(Todo todo) {
        log.info("{} {}", todo.getUsername(), todo.getDescription());
        repository.save(todo);
    }
}
