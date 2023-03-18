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

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service @RequiredArgsConstructor
public class BoardService {

    private final UserRepository userRepository;
    private final BoardRepository boardRepository;

            //날짜 string 예외처리
        private void validateBoard(Board board) {
            String startDateStr = board.getStartDate();
            String endDateStr = board.getEndDate();

            // startDate와 endDate를 LocalDate 타입으로 변환
            LocalDate startDate = LocalDate.parse(startDateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            LocalDate endDate = LocalDate.parse(endDateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            // startDate가 endDate보다 날짜가 같거나 빠른 경우 예외 처리
            if (startDate.isAfter(endDate) || startDate.isEqual(endDate)) {
                throw new ApiException(ExceptionEnum.UNAVAILABLE_FOR_LEGAL_REASONS);
            }
        }

    // 글생성
    @Transactional
    public MessageResponseDto writeBoard(BoardRequestDto boardRequestDto, User user) {
            Board board = new Board(boardRequestDto, user);
            validateBoard(board);
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
        validateBoard(board);
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




    //메인페이지 전체 글 리스트 조회  -토큰 x
    @Transactional(readOnly = true)
    public List<BoardListResponseDto> getBoardList() {
        List<BoardListResponseDto> boardListResponseDtos = new ArrayList<>(); //mapstream 사용해보기...
        List<Board> boards = boardRepository.findAllByOrderByCreatedAtDesc();
        for (Board board : boards) {
            boardListResponseDtos.add(new BoardListResponseDto(board));
        }
        return boardListResponseDtos;
    }

    // 상세페이지  -토큰 x
    @Transactional(readOnly = true)
    public BoardDetailResponseDto getBoardDetailList(Long boardId) {
        Board board = boardRepository.findById(boardId).orElseThrow(
                () -> new ApiException(ExceptionEnum.NOT_FOUND_POST_ALL)
        );
        return new BoardDetailResponseDto(board);
    }



    // 내 게시글 리스트 - 토큰 o
    @Transactional(readOnly = true)
    public List<BoardSimpleResponseDto> getBoardMyList(User user) {
        List<BoardSimpleResponseDto> boardSimpleResponseDtos = new ArrayList<>();
        List<Board> boards = boardRepository.findByUserid(user.getId());
        for (Board board : boards) {
            boardSimpleResponseDtos.add(new BoardSimpleResponseDto(board));
        }
        return boardSimpleResponseDtos;
    }

//  @Transactional(readOnly = true)
//    public List<BoardController.BoardSimpleResponseDto> getBoardMyList(User user) {
//        List<BoardController.BoardSimpleResponseDto> boardSimpleResponseDtos = new ArrayList<>();
//        List<Board> boards = boardRepository.findByUser(user);
//        for (Board board : boards) {
//            boardSimpleResponseDtos.add(new BoardController.BoardSimpleResponseDto(board));
//        }
//        return boardSimpleResponseDtos;
//    }
//}
}