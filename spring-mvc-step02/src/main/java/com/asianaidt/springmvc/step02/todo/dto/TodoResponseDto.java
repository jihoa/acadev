package com.asianaidt.springmvc.step02.todo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;

@Setter
@Getter
@ToString
public class TodoResponseDto {
    private long id;
    private  String username ;
    private String description;
    private Date targetDate;

}
