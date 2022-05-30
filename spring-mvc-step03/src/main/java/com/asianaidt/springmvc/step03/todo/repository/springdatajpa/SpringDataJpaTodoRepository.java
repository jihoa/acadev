package com.asianaidt.springmvc.step03.todo.repository.springdatajpa;

import com.asianaidt.springmvc.step03.todo.domain.Todo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SpringDataJpaTodoRepository extends CrudRepository<Todo, Long> {
    List<Todo> findByUsername(String username);


}
