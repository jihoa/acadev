package com.asianaidt.javaservlet.repository;

import com.asianaidt.javaservlet.domain.Todo;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public class TodoRepository {
    private static final AtomicLong sequence = new AtomicLong(0);
    //private static final List<Todo> todos = new ArrayList<>();
    private static final List<Todo> todos = new ArrayList<>(Arrays.asList(
            Todo.builder()
                    .id(sequence.incrementAndGet())
                    .description("user1에 대한 상세설명")
                    .username("user1")
                    .targetDate(new Date())
                    .build(),
            Todo.builder().id(sequence.incrementAndGet()).description("user2에 대한 상세 설명").username("user2").targetDate(new Date()).build()
    ));

    public List<Todo> findAll() {
        return todos;
    }

    public Todo save(final Todo todo) {
        todo.setId(sequence.incrementAndGet());
        todos.add(todo);
        return todo;
    }

    public Optional<Todo> findById(final long id) {
        return todos.stream()
                .filter(todo -> todo.getId() == id)
                .findAny();
    }

    public List<Todo> findByUsername(final String username) {
        return todos.stream().filter(todo -> todo.getUsername().equals(username)).collect(Collectors.toList());
    }

    public void clearData() {
        todos.clear();
    }
}
