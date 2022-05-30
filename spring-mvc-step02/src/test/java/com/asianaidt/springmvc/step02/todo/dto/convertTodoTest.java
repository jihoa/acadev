package com.asianaidt.springmvc.step02.todo.dto;


import com.asianaidt.springmvc.step02.todo.domain.Todo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class convertTodoTest {
    private TodoRequestDto todoRequestDto;

    @BeforeEach
    void setUp() {
        todoRequestDto = new TodoRequestDto();
        todoRequestDto.setUsername("Convert to Entity");
        todoRequestDto.setDescription("DTO를 Model 객체로 변환하는 테스트");
    }

    @DisplayName("생성자를 이용한 변환: DTO -> Model")
    @Test
    void test_convert_todo() {
        Todo todo = new Todo();
        todo.setUsername(todoRequestDto.getUsername());
        todo.setDescription(todoRequestDto.getDescription());

        assertAll(
                () -> assertThat(todo.getUsername()).isEqualTo(todoRequestDto.getUsername()),
                () -> assertThat(todo.getDescription()).isEqualTo(todoRequestDto.getDescription()));

    }

    @DisplayName("ModelMapper를 이용한 변환: DTO -> Model")
    @Test
    void test_convert_todo_use_model_mapper() {
        ModelMapper modelMapper = new ModelMapper();
        Todo todo = modelMapper.map(todoRequestDto, Todo.class);

        assertAll(
                () -> assertThat(todo.getUsername()).isEqualTo(todoRequestDto.getUsername()),
                () -> assertThat(todo.getDescription()).isEqualTo(todoRequestDto.getDescription()));
    }


}