package com.example.seoulcultureport.controller;

import com.example.seoulcultureport.dto.*;
import com.example.seoulcultureport.security.UserDetailsImpl;
import com.example.seoulcultureport.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import java.util.List;

//반환타입 Enum 으로 바꾸거나 Exception 처리하기 !
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board")
public class BoardController {

    private final BoardService boardService;

    // 글생성
    @PostMapping("/")
    public MessageResponseDto writeBoard(
            @RequestBody BoardRequestDto boardRequestDto,
            @AuthenticationPrincipal final UserDetailsImpl userDetails) {
        return boardService.writeBoard(boardRequestDto, userDetails.getUser());
    }

    // 내 게시글 수정
     @PutMapping("/{boardId}")
     public MessageResponseDto updateBoard(
            @PathVariable Long boardId,
            @RequestBody BoardRequestDto boardRequest,
            @AuthenticationPrincipal UserDetailsImpl userDetails){
        return boardService.updateBoard(boardId, boardRequest, userDetails.getUser());
    }

    // 내 게시글 삭제
      @DeleteMapping("/{boardiId}")
        public MessageResponseDto deleteBoard(@PathVariable Long boardId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
            return boardService.deleteBoard(boardId, userDetails.getUser());
        }

    //메인페이지 전체 글 [리스트] 조회 (토큰x)
        @GetMapping("/list")
        public List<BoardListResponseDto> getBoardList() {
            return boardService.getBoardList();
        }

    // 상세페이지 (토큰 x)
        @GetMapping("/detail/{boardId}")
        public  BoardDetailResponseDto getBoardDetailList(@PathVariable Long boardId) {
            return boardService.getBoardDetailList(boardId);
        }


    // 내 게시글 [리스트] (토큰 o)
        @GetMapping("/mylist")
        public List<BoardSimpleResponseDto> getBoardMyList(@AuthenticationPrincipal UserDetailsImpl userDetails) {
            return boardService.getBoardMyList(userDetails.getUser());
        }


}
