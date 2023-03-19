package com.example.seoulcultureport.service;

import com.example.seoulcultureport.dto.*;
import com.example.seoulcultureport.entity.User;
import com.example.seoulcultureport.exception.ApiException;
import com.example.seoulcultureport.exception.ExceptionEnum;
import com.example.seoulcultureport.jwt.JwtUtil;
import com.example.seoulcultureport.jwt.UserRoleEnum;
import com.example.seoulcultureport.repository.UserRepository;
import com.example.seoulcultureport.security.UserDetailsImpl;
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

        String username = signupRequestDto.getLoginid();
        String password = passwordEncoder.encode(signupRequestDto.getPassword());
        String nickname = signupRequestDto.getNickname();

        Optional<User> userfind = userRepository.findByUsername(username);
        if (userfind.isPresent()) {
            throw new ApiException(ExceptionEnum.DUPLICATE_USER);
        }

        Optional<User> nickfind = userRepository.findByNickname(signupRequestDto.getNickname());
        if (nickfind.isPresent()) {
            throw new ApiException(ExceptionEnum.DUPLICATE_NICKNAME);
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
    public LoginResponseDto login(LoginRequestDto loginRequestDto, HttpServletResponse response) {
        String username = loginRequestDto.getLoginid();
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
        return new LoginResponseDto(StatusEnum.OK, user.getNickname());
    }

    @Transactional(readOnly = true)
    public CheckIdResponseDto checkid(CheckIdRequestDto checkIdRequestDto) {

        String username = checkIdRequestDto.getLoginid();

        Optional<User> userfind = userRepository.findByUsername(username);
        if (userfind.isPresent()) {
            throw new ApiException(ExceptionEnum.DUPLICATE_USER);
        }

        return new CheckIdResponseDto("pass");
    }

    @Transactional
    public MessageResponseDto modifyPassword(ModifyPwRequestDto checkPwRequestDto,
                                             User user) {

        String userid = user.getUsername();
        String password = checkPwRequestDto.getPassword();
        String passwordNew = checkPwRequestDto.getPasswordNew();
        String passwordCheck = checkPwRequestDto.getPasswordCheck();

        User foundUser = userRepository.findByUsername(userid).orElseThrow(
                () -> new ApiException(ExceptionEnum.NOT_FOUND_USER)
        );

        if (!passwordEncoder.matches(password, foundUser.getPassword())) {
            throw new ApiException(ExceptionEnum.PASSWORD_MISMATCH);
        }
        if (!passwordNew.equals(passwordCheck)) {
            throw new ApiException(ExceptionEnum.PASSWORD_MISMATCH_NEW);
        }

        String newPassword = passwordEncoder.encode(passwordNew);
        foundUser.update(newPassword);


        return new MessageResponseDto(StatusEnum.OK);
    }

    @Transactional
    public MessageResponseDto nickpatch(PatchNickRequestDto patchNickRequestDto, User username, UserDetailsImpl userDetails) {

        String nickname = patchNickRequestDto.getNickname();

        Optional<User> nickfind = userRepository.findByNickname(nickname);
        if (nickfind.isPresent()) {
            throw new ApiException(ExceptionEnum.DUPLICATE_NICKNAME);
        }
        //사용자의 닉네임 변경
        if(!username.getUsername().equals(userDetails.getUsername())) {
            throw new ApiException(ExceptionEnum.TOKEN_ERROR);
        }
        username.setNickname(patchNickRequestDto.getNickname());

        //변경된 사용자 정보를 데이터베이스에 저장
        userRepository.save(username);

        return new MessageResponseDto(StatusEnum.OK);
    }
}
