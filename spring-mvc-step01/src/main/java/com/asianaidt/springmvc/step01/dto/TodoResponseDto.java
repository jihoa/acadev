package com.asianaidt.springmvc.step01.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class TodoResponseDto {
    private long id;
    private  String username ;
    private String description;
    private Date targetDate;

}
