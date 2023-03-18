package com.example.seoulcultureport.controller;

import com.example.seoulcultureport.dto.*;
import com.example.seoulcultureport.exception.ApiException;
import com.example.seoulcultureport.exception.ExceptionEnum;
import com.example.seoulcultureport.security.UserDetailsImpl;
import com.example.seoulcultureport.service.UserService;
import lombok.RequiredArgsConstructor;
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

    @PatchMapping("/modify/nick")
    public MessageResponseDto nickpatch(@Valid @RequestBody PatchNickRequestDto patchNickRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        if(userDetails == null) {
            throw new ApiException(ExceptionEnum.NOT_FOUND_TOKEN);
        }
        return userService.nickpatch(patchNickRequestDto, userDetails.getUser(), userDetails);
    }
}
