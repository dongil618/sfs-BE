package com.example.sfs.dto.auth;

import com.example.sfs.model.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoResponseDto {

    private String username;
    private String firstname;
    private String lastname;
    private String email;
    private List<String> roles;

    public UserInfoResponseDto(Member member, List<String> memberAuthorities) {
        this.username = member.getUsername();
        this.firstname = member.getFirstname();
        this.lastname = member.getLastname();
        this.email = member.getEmail();
        this.roles = memberAuthorities;
    }
}
