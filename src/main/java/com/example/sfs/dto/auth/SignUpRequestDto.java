package com.example.sfs.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequestDto {

    String username; // 아이디
    String email; // 이메일
    String firstname; // 이름
    String lastname; // 성
    String password; // 패스워드
    String checkPassword; // 확인용 패스워드
    String role; // 권한
}
