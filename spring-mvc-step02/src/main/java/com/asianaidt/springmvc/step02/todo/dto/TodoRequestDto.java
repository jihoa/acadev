package com.asianaidt.springmvc.step02.todo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class TodoRequestDto {
    private String username;
    private String description;


}
