package com.example.sfs.model;

import com.example.sfs.config.auth.MemberRoleEnum;
import com.example.sfs.dto.auth.SignUpRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;
    private String email;
    private String fullName;
    private String firstName;
    private String lastName;
    private String password;
    private MemberRoleEnum role;

    public Member(SignUpRequestDto signUpRequestDto, String encodePassword) {
        this.email = signUpRequestDto.getEmail();
        this.fullName = signUpRequestDto.getLastName() + signUpRequestDto.getFirstName();
        this.firstName = signUpRequestDto.getFirstName();
        this.lastName = signUpRequestDto.getLastName();
        this.password = encodePassword;
        this.role = signUpRequestDto.getRole();
    }
}
