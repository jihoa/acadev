package com.asianaidt.springrestful.step01.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberDTO {
    @NotBlank(message = "사용자이름은 필수 입력 사항입니다.")
    private String username;
    @NotBlank(message = "비밀번호는 필수 입력 사항입니다.")
    @Size(min = 8, max = 12, message = "비밀번호는 8~12 로 입력해주세요.")
    private String password;
    @NotNull
    @Email
    private String email;

    @Builder
    public MemberDTO(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

}
