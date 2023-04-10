package com.example.sfs.dto.auth;

import com.example.sfs.config.auth.MemberRoleEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequestDto {

    String email; // 이메일
    String firstName; // 이름
    String lastName; // 성
    String password; // 패스워드
    String checkPassword; // 확인용 패스워드
    MemberRoleEnum role; // 권한
}
