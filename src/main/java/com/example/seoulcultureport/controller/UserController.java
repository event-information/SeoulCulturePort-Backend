package com.example.seoulcultureport.controller;

import com.example.seoulcultureport.dto.*;
import com.example.seoulcultureport.exception.ApiException;
import com.example.seoulcultureport.exception.ExceptionEnum;
import com.example.seoulcultureport.security.UserDetailsImpl;
import com.example.seoulcultureport.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity login(
            @RequestBody @Valid LoginRequestDto loginRequestDto,
            HttpServletResponse response) {
        return ResponseEntity.ok().body(userService.login(loginRequestDto, response));
    }

    @PostMapping("/check")
    public CheckIdResponseDto checkid(
            @RequestBody @Valid CheckIdRequestDto checkIdRequestDto) {
        return userService.checkid(checkIdRequestDto);
    }

    @PostMapping("/checknick")
    public CheckNickResponseDto checknick(
            @RequestBody @Valid CheckNickRequestDto checkNickRequestDto) {
        return userService.checknick(checkNickRequestDto);
    }

    @PatchMapping("/modify/nick")
    public MessageResponseDto nickpatch(
            @Valid @RequestBody PatchNickRequestDto patchNickRequestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return userService.nickpatch(patchNickRequestDto, userDetails.getUser(), userDetails);
    }

    @PatchMapping("/modify/pw")
    public MessageResponseDto modifyPassword(
            @RequestBody @Valid ModifyPwRequestDto checkPwRequestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return userService.modifyPassword(checkPwRequestDto, userDetails.getUser());
    }
}
