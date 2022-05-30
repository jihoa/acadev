package com.asianaidt.springmvc.step03.todo.service;

import com.asianaidt.springmvc.step03.todo.domain.Todo;
import com.asianaidt.springmvc.step03.todo.persistence.mybatis.TodoMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;
import java.util.List;
import java.util.Optional;


public class MybatisTodoServiceImpl implements TodoService {
    @Autowired
    TodoMapper todoMapper;

    @Override
    public List<Todo> getTodosByUser(String user) {
        return todoMapper.getTodosByUser(user);
    }

    @Override
    public Optional<Todo> getTodoById(long id) {
        return todoMapper.getTodoById(id);
    }

    @Override
    public void updateTodo(Todo todo) {
        todoMapper.updateTod(todo);
    }

    @Override
    public void addTodo(String name, String desc, Date targetDate, Boolean isDone) {
        todoMapper.save(new Todo(name, desc, targetDate));
    }

    @Override
    public void deleteTodo(long id) {
        Optional<Todo> todo = todoMapper.getTodoById(id);
        if (todo.isPresent()) {
            todoMapper.delete(todo.get());
        }
    }

    @Override
    public void saveTodo(Todo todo) {
        todoMapper.save(todo);
    }
}
