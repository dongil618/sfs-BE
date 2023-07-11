package com.example.sfs.model;

import com.example.sfs.dto.auth.SignUpRequestDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Member {

    @JsonIgnore
    @Id
    @Column(name = "MEMBER_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEMBER_SEQ")
    @SequenceGenerator(name = "MEMBER_SEQ", sequenceName = "MEMBER_SEQ", allocationSize = 1)
    private Long id;

    @JsonIgnore
    @Column(name = "USERNAME", length = 100)
    @NotNull
    @Size(min = 4, max = 100)
    private String username;


    @JsonIgnore
    @Column(name = "PASSWORD", length = 100)
    @NotNull
    @Size(min = 4, max = 100)
    private String password;

    @Column(name = "FIRSTNAME", length = 50)
    @NotNull
    @Size(min = 1, max = 50)
    private String firstname;

    @Column(name = "LASTNAME", length = 50)
    @NotNull
    @Size(min = 1, max = 50)
    private String lastname;

    @Column(name = "EMAIL", length = 50)
    @NotNull
    @Size(min = 4, max = 50)
    private String email;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @JsonIgnore
    @Column(name = "ACTIVATED")
    @NotNull
    private boolean activated;

    public Member(SignUpRequestDto signUpRequestDto, String encodePassword) {
        this.username = signUpRequestDto.getUsername();
        this.email = signUpRequestDto.getEmail();
        this.firstname = signUpRequestDto.getFirstname();
        this.lastname = signUpRequestDto.getLastname();
        this.password = encodePassword;
        this.activated = true;
    }

    public Member(Long memberId, String username) {
        this.id = memberId;
        this.username = username;
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
