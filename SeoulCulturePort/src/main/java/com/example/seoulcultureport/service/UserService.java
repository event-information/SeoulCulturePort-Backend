package com.example.seoulcultureport.service;

import com.example.seoulcultureport.dto.LoginRequestDto;
import com.example.seoulcultureport.dto.MessageResponseDto;
import com.example.seoulcultureport.dto.SignupRequestDto;
import com.example.seoulcultureport.dto.StatusEnum;
import com.example.seoulcultureport.entity.User;
import com.example.seoulcultureport.exception.ApiException;
import com.example.seoulcultureport.exception.ExceptionEnum;
import com.example.seoulcultureport.jwt.JwtUtil;
import com.example.seoulcultureport.jwt.UserRoleEnum;
import com.example.seoulcultureport.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    private final String ADMIN_TOKEN = "asdfasdfasdf";

    @Transactional
    public MessageResponseDto signup(SignupRequestDto signupRequestDto) {

        String username = signupRequestDto.getUsername();
        String password = passwordEncoder.encode(signupRequestDto.getPassword());
        String nickname = signupRequestDto.getNickname();

        Optional<User> userfind = userRepository.findByUsername(username);
        if (userfind.isPresent()) {
            throw new ApiException(ExceptionEnum.DUPLICATE_USER);
        }
        UserRoleEnum role = UserRoleEnum.USER;
        if (signupRequestDto.isAdmin()) {
            if (!signupRequestDto.getAdminToken().equals(ADMIN_TOKEN)) {
                throw new IllegalArgumentException("관리자 암호가 일치하지 않습니다.");
            }
            role = UserRoleEnum.ADMIN;
        }
        userRepository.save(new User(username, password, role, nickname));
        return new MessageResponseDto(StatusEnum.OK);
    }

    @Transactional(readOnly = true)
    public MessageResponseDto login(LoginRequestDto loginRequestDto, HttpServletResponse response) {
        String username = loginRequestDto.getUsername();
        String password = loginRequestDto.getPassword();

        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new ApiException(ExceptionEnum.NOT_FOUND_USER)
        );

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new ApiException(ExceptionEnum.PASSWORD_MISMATCH);
        }

        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(
                user.getUsername(),
                user.getRole(),
                user.getNickname()));
        return new MessageResponseDto(StatusEnum.OK);
    }
}
