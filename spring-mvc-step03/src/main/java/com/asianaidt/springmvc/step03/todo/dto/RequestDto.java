package com.asianaidt.springmvc.step03.todo.dto;

import com.asianaidt.springmvc.step03.todo.domain.Todo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@NoArgsConstructor
@Setter @Getter
@ApiModel("RequestDto")
public class RequestDto {
    private long id ;
    @ApiModelProperty("사용자이름")
    private String username;

    @NotNull
    @Size(min = 10, max = 50, message = "10자 이상 입력하세요...")
    @ApiModelProperty("설명")
    private String description;

    @NotNull
    @ApiModelProperty("완료일자")
    private Date targetDate;

    public Todo toEntity() {
        Todo todo = new Todo();
        todo.setId(this.id);
        todo.setUsername(this.username);
        todo.setDescription(this.description);
        todo.setTargetDate(this.targetDate);

        return todo;
    }

}

