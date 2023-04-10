package com.example.sfs.controller;

import com.example.sfs.dto.auth.SignUpRequestDto;
import com.example.sfs.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
