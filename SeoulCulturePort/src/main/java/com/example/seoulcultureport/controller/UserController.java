package com.example.seoulcultureport.controller;

import com.example.seoulcultureport.dto.LoginRequestDto;
import com.example.seoulcultureport.dto.MessageResponseDto;
import com.example.seoulcultureport.dto.SignupRequestDto;
import com.example.seoulcultureport.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public MessageResponseDto signup(
            @RequestBody @Valid SignupRequestDto signupRequestDto) {
        return userService.signup(signupRequestDto);
    }

    @PostMapping("/login")
    public MessageResponseDto login(
            @RequestBody @Valid LoginRequestDto loginRequestDto,
            HttpServletResponse response) {
        return userService.login(loginRequestDto, response);
    }
}
