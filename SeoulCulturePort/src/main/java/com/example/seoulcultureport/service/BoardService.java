package com.example.seoulcultureport.service;

import com.example.seoulcultureport.dto.*;
import com.example.seoulcultureport.entity.Board;
import com.example.seoulcultureport.repository.BoardRepository;
import com.example.seoulcultureport.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service @RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final UserService userService;

    // 글생성
    @Transactional
    public BoardDetailResponseDto writeBoard(BoardRequestDto boardRequestDto, User user) {
        Board board = boardRepository.saveAndFlush(new Board(boardRequestDto, user));

        return new BoardDetailResponseDto(board);
    }

    // 내 게시글 수정
    @Transactional
    public MessageResponseDto updateBoard(Long id,
                                          BoardRequestDto boardRequestDto,
                                          User user ) {

        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("게시판을 찾을 수 없습니다.")
        );

        boardRepository.findByIdAndUserid(id, user.getId()).orElseThrow(
                () -> new ApiException(ExceptionEnum.NOT_FOUND_POST)
        );

        board.update(boardRequestDto);
        return new MessageResponseDto(StatusEnum.OK);
    }


    // 내 게시글 삭제
    @Transactional
    public MessageResponseDto deleteBoard(Long id, User user) {
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("")
        );
        if(!board.getUserid().equals(user.getId())) {
            throw new IllegalArgumentException("게시판 삭제 권한이 없습니다.");
        }
        boardRepository.deleteById(id);
        return new MessageResponseDto(StatusEnum.OK);
    }




    //메인페이지 전체 글 리스트 조회
    @Transactional(readOnly = true)
    public List<BoardListResponseDto> getBoardList() {
        List<BoardListResponseDto> boardListResponseDtos = new ArrayList<>();
        List<Board> boards = boardRepository.findAllByOrderByCreatedAtDesc();
        for (Board board : boards) {
            boardListResponseDtos.add(new BoardListResponseDto(board));
        }
        return boardListResponseDtos;
    }

    // 상세페이지
    @Transactional(readOnly = true)
    public BoardDetailResponseDto getBoardDetailList(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("")
        );
        return new BoardDetailResponseDto(board);
    }



    // 내 게시글 리스트
    @Transactional(readOnly = true)
    public List<BoardSimpleResponseDto> getBoardMyList(User user) {
        List<BoardSimpleResponseDto> boardSimpleResponseDtos = new ArrayList<>();
        List<Board> boards = boardRepository.findByUserid(user.getId());
    }


}