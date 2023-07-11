package com.example.sfs.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestDto {
    @NotNull
    private String username;    // 아이디
    @NotNull
    private String password;    // 패스워드
    private Boolean rememberMe;
    public Boolean isRememberMe() {
        return rememberMe;
    }
}
