package com.asianaidt.springmvc.step02.todo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@ApiModel("Todo")
public class Todo {
    @ApiModelProperty
    @JsonIgnore
    private long id;

    @ApiModelProperty
    private String username;

    @ApiModelProperty("할일에 대한 설명")
    private String description;

    @ApiModelProperty("완료일자")
    private Date targetDate;

    @ApiModelProperty("완료여부")
    private boolean completed;

    public Todo(long id, String username, String description, Date targetDate) {
        this.id = id;
        this.username = username;
        this.description = description;
        this.targetDate = targetDate;
        this.completed = false;
    }

}
