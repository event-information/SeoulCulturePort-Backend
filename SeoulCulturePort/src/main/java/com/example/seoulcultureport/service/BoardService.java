package com.example.seoulcultureport.service;

import com.example.seoulcultureport.dto.*;
import com.example.seoulcultureport.entity.Board;
import com.example.seoulcultureport.entity.User;
import com.example.seoulcultureport.exception.ApiException;
import com.example.seoulcultureport.exception.ExceptionEnum;
import com.example.seoulcultureport.repository.BoardRepository;
import com.example.seoulcultureport.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service @RequiredArgsConstructor
public class BoardService {

    private final UserRepository userRepository;
    private final BoardRepository boardRepository;

    // 글생성
    @Transactional
    public MessageResponseDto writeBoard(BoardRequestDto boardRequestDto, User user) {
        boardRepository.saveAndFlush(new Board(boardRequestDto, user));
        return new MessageResponseDto(StatusEnum.OK);
    }

    // 내 게시글 수정
    @Transactional
    public MessageResponseDto updateBoard(Long boardId,
                                          BoardRequestDto boardRequestDto,
                                          User user ) {

        Board board = boardRepository.findById(boardId).orElseThrow(
                () -> new ApiException(ExceptionEnum.NOT_FOUND_POST_ALL)
        );

        boardRepository.findByIdAndUserid(boardId, user.getId()).orElseThrow(
                () -> new ApiException(ExceptionEnum.NOT_FOUND_POST)
        );

        board.update(boardRequestDto);
        return new MessageResponseDto(StatusEnum.OK);
    }


    // 내 게시글 삭제
    @Transactional
    public MessageResponseDto deleteBoard(Long boardId, User user) {
        Board board = boardRepository.findById(boardId).orElseThrow(
                () -> new ApiException(ExceptionEnum.NOT_FOUND_POST_ALL)
        );
        if(!board.getUserid().equals(user.getId())) {
            throw  new ApiException(ExceptionEnum.NOT_FOUND_POST);
        }
        boardRepository.deleteById(boardId);
        return new MessageResponseDto(StatusEnum.OK);
    }




    //메인페이지 전체 글 리스트 조회
    @Transactional(readOnly = true)
    public List<BoardListResponseDto> getBoardList() {
        List<BoardListResponseDto> boardListResponseDtos = new ArrayList<>(); //mapstream 사용해보기...
        List<Board> boards = boardRepository.findAllByOrderByCreatedAtDesc();
        for (Board board : boards) {
            boardListResponseDtos.add(new BoardListResponseDto(board));
        }
        return boardListResponseDtos;
    }

    // 상세페이지
    @Transactional(readOnly = true)
    public BoardDetailResponseDto getBoardDetailList(Long boardId) {
        Board board = boardRepository.findById(boardId).orElseThrow(
                () -> new ApiException(ExceptionEnum.NOT_FOUND_POST_ALL)
        );
        return new BoardDetailResponseDto(board);
    }



    // 내 게시글 리스트
    @Transactional(readOnly = true)
    public List<BoardSimpleResponseDto> getBoardMyList(User user) {
        List<BoardSimpleResponseDto> boardSimpleResponseDtos = new ArrayList<>();
        List<Board> boards = boardRepository.findByUserid(user.getId());
        for (Board board : boards) {
            boardSimpleResponseDtos.add(new BoardSimpleResponseDto(board));
        }
        return boardSimpleResponseDtos;
    }


}