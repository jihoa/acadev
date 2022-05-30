package com.asianaidt.springmvc.step03.todo.domain;

import com.asianaidt.springmvc.step03.todo.dto.RequestDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;


@ApiModel("TODO")
@Entity
@Table(name = "todo")
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id ;
    @ApiModelProperty(value = "username", name = "username", dataType = "String", example = "yhkim")
    @Column(name = "user_name")
    private String username;

    @NotNull
    @Size(min = 10, max = 50, message = "10자 이상 입력하세요...")
    @ApiModelProperty("설명")
    private String description;

    @ApiModelProperty("완료일자")
    @Column(name="target_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    @CreationTimestamp
    private Date targetDate;

    //생성자
    public Todo() {
        super();
    }

    public Todo(String username, String description, Date targetDate) {
        super();
        this.username = username;
        this.description = description;
        this.targetDate = targetDate;
    }

    public RequestDto toDto() {
        RequestDto requestDto = new RequestDto();
        requestDto.setId(this.id);
        requestDto.setUsername(this.username);
        requestDto.setDescription(this.description);
        requestDto.setTargetDate(this.targetDate);

        return requestDto;
    }

    //setter... getter...

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(Date targetDate) {
        this.targetDate = targetDate;
    }


}
