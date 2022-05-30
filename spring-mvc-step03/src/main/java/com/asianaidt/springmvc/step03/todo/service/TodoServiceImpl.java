package com.asianaidt.springmvc.step03.todo.service;

import com.asianaidt.springmvc.step03.todo.domain.Todo;
import com.asianaidt.springmvc.step03.todo.repository.TodoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class TodoServiceImpl implements TodoService {

    // 생성자 DI
    private final TodoRepository todoRepository;

    public TodoServiceImpl(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    public List<Todo> getTodosByUser(String user) {
        return todoRepository.findByUsername(user);
    }

    @Override
    public Optional<Todo> getTodoById(long id) {
        return todoRepository.findById(id);
    }

    @Override
    public void updateTodo(Todo todo) {
        todoRepository.update(todo);
    }

    @Override
    public void addTodo(String name, String desc, Date targetDate, Boolean isDone) {
        todoRepository.save(new Todo(name, desc, targetDate));
    }

    @Override
    public void deleteTodo(long id) {
        Optional<Todo> todo = todoRepository.findById(id);
        if (todo.isPresent()) {
            todoRepository.delete(todo.get());
        }
    }

    @Override
    public void saveTodo(Todo todo) {
        log.info("{} {}", todo.getUsername(), todo.getDescription());
        todoRepository.save(todo);
    }
}
