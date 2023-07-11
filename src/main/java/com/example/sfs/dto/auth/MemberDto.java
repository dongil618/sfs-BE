package com.example.sfs.dto.auth;

import com.example.sfs.model.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberDto {
    private Long memberId;
    private String username;
    private String email;

    public MemberDto(Member member) {
        this.memberId = member.getId();
        this.username = member.getUsername();
        this.email = member.getEmail();
    }
}
