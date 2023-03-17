package com.example.seoulcultureport.controller;

import com.example.seoulcultureport.dto.*;
import com.example.seoulcultureport.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController @RequiredArgsConstructor
@RequestMapping("/api/board")
public class BoardController {
    private final BoardService boardService;

    // 글생성
    @PostMapping("/")
    public ResponseEntity<BoardResponseDto> writeBoard(@RequestBody BoardRequestDto boardRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        BoardDetailResponseDto response = boardService.writeBoard(boardRequestDto, userDetails.getUser());
        return new ResponseEntity.ok(new BoardResponseDto (200,"success"));
    }

    // 내 게시글 수정
     @PutMapping("/{id}")
        public ResponseEntity<BoardResponseDto> updateBoard(@PathVariable Long id, @RequestBody BoardRequestDto boardRequest, @AuthenticationPrincipal UserDetailsImpl userDetails){
         BoardDetailResponseDto response = boardService.updateBoard(id, boardRequest, userDetails.getUser());
            return new ResponseEntity.ok(new BoardResponseDto(200,"success"));  //Enum으로 ㅅ쓰세요 !!
        }

    // 내 게시글 삭제
      @DeleteMapping("/{id}")
        public ResponseEntity<BoardResponseDto> deleteBoard(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
            boardService.deleteBoard(id, userDetails.getUser());
            return new ResponseEntity.ok(new BoardResponseDto(200,"success"));
        }

    //메인페이지 전체 글 리스트 조회
        @GetMapping("/list")
        public ResponseEntity<BoardResponseDto> getBoardList() {
            List<BoardListResponseDto> response = boardService.getBoardList();
            return new ResponseEntity.ok(new BoardResponseDto(200,"success"));
        }

    // 상세페이지
        @GetMapping("/detail/{id}")
        public ResponseEntity<BoardResponseDto> getBoardDetailList(@PathVariable Long id) {
            BoardDetailResponseDto response = boardService.getBoardDetailList(id);
            return new  ResponseEntity.ok(new BoardResponseDto(200,"success"));
        }


    // 내 게시글 리스트
        @GetMapping("/mylist")
        public ResponseEntity<BoardResponseDto> getBoardMyList(@AuthenticationPrincipal UserDetailsImpl userDetails) {
            List<BoardSimpleResponseDto> response = boardService.getBoardMyList(userDetails.getUser());
            return new ResponseEntity.ok(new BoardResponseDto(200,"success"));
        }


}
