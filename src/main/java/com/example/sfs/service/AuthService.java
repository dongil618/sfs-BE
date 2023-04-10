package com.example.sfs.service;

import com.example.sfs.config.WebSecurityConfig;
import com.example.sfs.dto.auth.SignUpRequestDto;
import com.example.sfs.model.Member;
import com.example.sfs.repository.AuthRepository;
import com.example.sfs.util.CommonUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthRepository authRepository;
    private final WebSecurityConfig webSecurityConfig;

    public Void saveMember(SignUpRequestDto signUpRequestDto) {
        String email = signUpRequestDto.getEmail();

        // email valid
        if(!CommonUtil.checkEmail(email)){
            throw new IllegalArgumentException("Not email");
        }

        // password valid
        String password = signUpRequestDto.getPassword();
        String checkPassword = signUpRequestDto.getCheckPassword();
        if(!checkPassword.equals(password)) {
            throw new IllegalArgumentException("Check password and password are different");
        }
        String encodePassword = webSecurityConfig.encodePwd().encode(password);

        // DB에 저장 유무 확인
        Member member = authRepository.findByEmail(email);
        if(member != null) {
            throw new IllegalArgumentException("Already exist member");
        }
        authRepository.save(new Member(signUpRequestDto, encodePassword));

        return null;
    }
}
