package com.asianaidt.springrestful.step01.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Entity
@Table
public class User implements Serializable {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String password;

    @Column
    private String email;

    @Column
    private String principal;

    @Column
    @Enumerated(EnumType.STRING)
    private SocialType socialType;

    @Column
    private LocalDateTime createDate;

    @Column
    private LocalDateTime updateDate;

    @Builder
    public User(String name, String password, String email, String principal, SocialType socialType, LocalDateTime createDate, LocalDateTime updateDate){
        this.name = name;
        this.password = password;
        this.email = email;
        this.principal = principal;
        this.socialType = socialType;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }
}
