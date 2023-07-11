package com.example.sfs.security.auth;

import com.example.sfs.model.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class UserDetailsImpl implements UserDetails {
    private Member member;
    private List<GrantedAuthority> grantedAuthorities;

    public UserDetailsImpl(Member member, List<GrantedAuthority> grantedAuthorities) {
        this.member = member;
        this.grantedAuthorities = grantedAuthorities;
    }

    public Long getMemberId() {
        return member.getId();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return member.getPassword();
    }

    @Override
    public String getUsername() {
        return member.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
