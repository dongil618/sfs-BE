package com.example.sfs.controller;

import com.example.sfs.dto.auth.LoginRequestDto;
import com.example.sfs.dto.auth.SignUpRequestDto;
import com.example.sfs.dto.auth.UserInfoResponseDto;
import com.example.sfs.security.auth.UserDetailsImpl;
import com.example.sfs.security.jwt.JwtFilter;
import com.example.sfs.security.jwt.JwtToken;
import com.example.sfs.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/sign-up")
    public ResponseEntity<Void> saveMember(@RequestBody SignUpRequestDto signUpRequestDto) {
        Void result = authService.saveMember(signUpRequestDto);
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<JwtToken> authorize(@Valid @RequestBody LoginRequestDto loginRequestDto) {

        String jwt = authService.createJwt(loginRequestDto);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);

        return new ResponseEntity<>(new JwtToken(jwt), httpHeaders, HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<UserInfoResponseDto> getMemberInfo(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        if(userDetails == null) {
            throw new IllegalArgumentException("no access");
        }

        Long memberId = userDetails.getMemberId();
        UserInfoResponseDto userInfoResponseDto = authService.getMemberInfo(memberId);
        return new ResponseEntity<>(userInfoResponseDto, HttpStatus.OK);
    }
}
