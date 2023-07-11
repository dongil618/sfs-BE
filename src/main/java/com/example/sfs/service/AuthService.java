package com.example.sfs.service;

import com.example.sfs.config.WebSecurityConfig;
import com.example.sfs.dto.auth.LoginRequestDto;
import com.example.sfs.dto.auth.SignUpRequestDto;
import com.example.sfs.dto.auth.UserInfoResponseDto;
import com.example.sfs.model.Authority;
import com.example.sfs.model.Member;
import com.example.sfs.model.MemberAuthority;
import com.example.sfs.repository.AuthorityRepository;
import com.example.sfs.repository.MemberAuthorityRepository;
import com.example.sfs.repository.MemberRepository;
import com.example.sfs.security.auth.UserDetailsImpl;
import com.example.sfs.security.jwt.TokenProvider;
import com.example.sfs.util.CommonUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final MemberRepository memberRepository;
    private final WebSecurityConfig webSecurityConfig;
    private final AuthorityRepository authorityRepository;
    private final MemberAuthorityRepository memberAuthorityRepository;
    private final TokenProvider tokenProvider;


    public Void saveMember(SignUpRequestDto signUpRequestDto) {
        // username valid
        String username = signUpRequestDto.getUsername();
        if(username == null) {
            throw new IllegalArgumentException("Username is necessary");
        }

        // password valid
        String email = signUpRequestDto.getEmail();
        if(email == null) {
            throw new IllegalArgumentException("Email is necessary");
        }
        if(!CommonUtil.checkEmail(email)){
            throw new IllegalArgumentException("Not email");
        }

        // password valid
        String password = signUpRequestDto.getPassword();
        String checkPassword = signUpRequestDto.getCheckPassword();
        if(password == null) {
            throw new IllegalArgumentException("password is necessary");
        }
        if(checkPassword == null) {
            throw new IllegalArgumentException("checkPassword is necessary");
        }
        if(!checkPassword.equals(password)) {
            throw new IllegalArgumentException("Check password and password are different");
        }
        String encodePassword = webSecurityConfig.encodePwd().encode(password);

        try {
            Member savedMember = memberRepository.save(new Member(signUpRequestDto, encodePassword));

            // 권한 확인
            String role = signUpRequestDto.getRole();
            Authority authority = authorityRepository.findByRole(role);
            if(authority == null) {
                throw new IllegalArgumentException("Not exist role");   // 존재하지 않는 권한
            }
            memberAuthorityRepository.save(new MemberAuthority(savedMember, authority));

        } catch(Exception e) {
            throw new IllegalArgumentException("Fail Sign Up : " + e.getMessage());
        }
        return null;
    }

    public String createJwt(LoginRequestDto loginRequestDto) {
        try {
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                    = new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(), loginRequestDto.getPassword());
            Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

            Long memberId = userDetails.getMemberId();
            Set<MemberAuthority> memberAuthorities = memberAuthorityRepository.findAllByMemberId(memberId);

            boolean rememberMe = (loginRequestDto.isRememberMe() == null) ? false : loginRequestDto.isRememberMe();
            return tokenProvider.createToken(userDetails.getMember(), memberAuthorities, rememberMe);
        }catch (Exception e){
            throw new IllegalArgumentException("인증되지 않았습니다");
        }
    }

    public UserInfoResponseDto getMemberInfo(Long memberId) {
        Member member = memberRepository.findById(memberId).get();
        if(member == null) {
            throw new IllegalArgumentException("no member");
        }
        Set<MemberAuthority> memberAuthorities = memberAuthorityRepository.findAllByMemberId(memberId);
        if(memberAuthorities == null) {
            throw new IllegalArgumentException("no have authorities");
        }
        List<String> memberAuthoritiesToString = memberAuthorities.stream()
                .map(authority -> authority.getAuthority().getRole())
                .collect(Collectors.toList());
        return new UserInfoResponseDto(member, memberAuthoritiesToString);
    }
}
