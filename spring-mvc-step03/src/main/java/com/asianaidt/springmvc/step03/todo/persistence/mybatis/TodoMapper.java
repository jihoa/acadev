package com.asianaidt.springmvc.step03.todo.persistence.mybatis;

import com.asianaidt.springmvc.step03.todo.domain.Todo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface TodoMapper {
    List<Todo> findAll();

    List<Todo> getTodosByUser(String user);

    Optional<Todo> getTodoById(long id);

    void updateTod(Todo todo);

    void save(Todo todo);

    void delete(Todo todo);
}
