package com.example.seoulcultureport.exception;

import org.springframework.http.HttpStatus;

public enum ExceptionEnum {

    DUPLICATE_USER(HttpStatus.BAD_REQUEST, "사용중인 아이디 입니다."),
    DUPLICATE_NICKNAME(HttpStatus.BAD_REQUEST, "사용중인 닉네임 입니다."),
    TOKEN_ERROR(HttpStatus.BAD_REQUEST, "Token Error"),
    PASSWORD_MISMATCH(HttpStatus.BAD_REQUEST, "비밀번호가 일치하지 않습니다."),
    NOT_FOUND_USER(HttpStatus.BAD_REQUEST, "회원을 찾을 수 없습니다."),

    NOT_FOUND_POST_ALL(HttpStatus.BAD_REQUEST, "게시글이 없습니다."),
    NOT_FOUND_POST(HttpStatus.BAD_REQUEST, "게시글 수정 권한이 없습니다."),
    NOT_FOUND_POST_ADMIN(HttpStatus.BAD_REQUEST, "게시물을 찾을 수 없습니다.(관리자계정)"),


    NOT_FOUND_COMMENT_ALL(HttpStatus.BAD_REQUEST, "댓글이 없습니다."),
    NOT_FOUND_COMMENT(HttpStatus.BAD_REQUEST, "댓글 수정 권한이 없습니다."),
    NOT_FOUND_COMMENT_ADMIN(HttpStatus.BAD_REQUEST, "댓글을 찾을 수 없습니다.(관리자계정)"),

    NOT_FOUND_TOKEN(HttpStatus.BAD_REQUEST, "토큰 값이 없습니다."),

    UNAVAILABLE_FOR_LEGAL_REASONS(HttpStatus.BAD_REQUEST, "시작일이 종료일보다 같거나 빠를 수 없습니다." )

    ;

    private HttpStatus status;
    private String message;

    public HttpStatus getStatus() {
        return this.status;
    }

    public String getMessage() {
        return this.message;
    }

    ExceptionEnum(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
