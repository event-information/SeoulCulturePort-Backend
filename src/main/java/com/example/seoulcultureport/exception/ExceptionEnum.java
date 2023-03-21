package com.example.seoulcultureport.exception;

import org.springframework.http.HttpStatus;

public enum ExceptionEnum {

    DUPLICATE_USER(HttpStatus.CONFLICT, "사용중인 아이디 입니다."), //409
    DUPLICATE_NICKNAME(HttpStatus.CONFLICT, "사용중인 닉네임 입니다."), //409
    TOKEN_ERROR(HttpStatus.UNAUTHORIZED, "Token Error"), //401
    PASSWORD_MISMATCH(HttpStatus.BAD_REQUEST, "비밀번호가 일치하지 않습니다."), //400
    PASSWORD_MISMATCH_NEW(HttpStatus.BAD_REQUEST, "새로운 비밀번호와 확인 비밀번호가 일치하지 않습니다."), //400
    NOT_FOUND_USER(HttpStatus.NOT_FOUND, "회원을 찾을 수 없습니다."), //404
    NOT_FOUND_POST_ALL(HttpStatus.NOT_FOUND, "게시글이 없습니다."), //404
    NOT_FOUND_POST(HttpStatus.FORBIDDEN, "게시글 수정 권한이 없습니다."), //403
    NOT_FOUND_POST_ADMIN(HttpStatus.NOT_FOUND, "게시물을 찾을 수 없습니다. (관리자 계정)"), //404
    NOT_FOUND_COMMENT_ALL(HttpStatus.NOT_FOUND, "댓글이 없습니다."), // 404
    NOT_FOUND_COMMENT(HttpStatus.FORBIDDEN, "댓글 수정 권한이 없습니다."), // 403
    NOT_FOUND_COMMENT_ADMIN(HttpStatus.NOT_FOUND, "댓글을 찾을 수 없습니다. (관리자 계정)"), //404
    NOT_FOUND_TOKEN(HttpStatus.UNAUTHORIZED, "토큰 값이 Null 입니다."), //401
    UNAVAILABLE_FOR_LEGAL_REASONS(HttpStatus.BAD_REQUEST, "시작일이 종료일보다 같거나 빠를 수 없습니다."), //400
    ALREADY_THUMBSUP(HttpStatus.BAD_REQUEST, "이미 좋아요를 누르셨습니다."),
    NOT_FOUND_THUMBSUP(HttpStatus.BAD_REQUEST, "취소 할 좋아요가 없습니다.");

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
