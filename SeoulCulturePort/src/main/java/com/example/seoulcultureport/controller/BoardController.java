package com.example.seoulcultureport.controller;

import com.example.seoulcultureport.dto.*;
import com.example.seoulcultureport.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

//반환타입 Enum 으로 바꾸거나 Exception 처리하기 !

@RestController @RequiredArgsConstructor
@RequestMapping("/api/board")
public class BoardController {
    private final BoardService boardService;

    // 글생성
    @PostMapping("/")
    public ResponseEntity<BoardResponseDto> writeBoard(@RequestBody BoardRequestDto boardRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        BoardDetailResponseDto response = boardService.writeBoard(boardRequestDto, userDetails.getUser());
        return ResponseEntity.ok(new BoardResponseDto(200, "success"));
    }

    // 내 게시글 수정
     @PutMapping("/{id}")
        public ResponseEntity<BoardResponseDto> updateBoard(@PathVariable Long id, @RequestBody BoardRequestDto boardRequest, @AuthenticationPrincipal UserDetailsImpl userDetails){
         BoardDetailResponseDto response = boardService.updateBoard(id, boardRequest, userDetails.getUser());
            return ResponseEntity.ok(new BoardResponseDto(200,"success"));
        }

    // 내 게시글 삭제
      @DeleteMapping("/{id}")
        public ResponseEntity<BoardResponseDto> deleteBoard(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
            boardService.deleteBoard(id, userDetails.getUser());
            return ResponseEntity.ok(new BoardResponseDto(200,"success"));
        }

    //메인페이지 전체 글 [리스트] 조회 (토큰x)
        @GetMapping("/list")
        public ResponseEntity<BoardResponseDto> getBoardList() {
            List<BoardListResponseDto> response = boardService.getBoardList();
            return ResponseEntity.ok(new BoardResponseDto(200,"success"));
        }

    // 상세페이지 (토큰 x)
        @GetMapping("/detail/{id}")
        public ResponseEntity<BoardResponseDto> getBoardDetailList(@PathVariable Long id) {
            BoardDetailResponseDto response = boardService.getBoardDetailList(id);
            return ResponseEntity.ok(new BoardResponseDto(200,"success"));
        }


    // 내 게시글 [리스트] (토큰 o)
        @GetMapping("/mylist")
        public ResponseEntity<BoardResponseDto> getBoardMyList(@AuthenticationPrincipal UserDetailsImpl userDetails) {
            List<BoardSimpleResponseDto> response = boardService.getBoardMyList(userDetails.getUser());
            return ResponseEntity.ok(new BoardResponseDto(200,"success"));
        }


}
