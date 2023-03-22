package com.example.seoulcultureport.service;

import com.example.seoulcultureport.dto.*;
import com.example.seoulcultureport.dto.boardDto.BoardDetailResponseDto;
import com.example.seoulcultureport.dto.boardDto.BoardListResponseDto;
import com.example.seoulcultureport.dto.boardDto.BoardRequestDto;
import com.example.seoulcultureport.dto.boardDto.BoardSimpleResponseDto;
import com.example.seoulcultureport.entity.Board;
import com.example.seoulcultureport.entity.Comment;
import com.example.seoulcultureport.entity.Thumbsup;
import com.example.seoulcultureport.entity.User;
import com.example.seoulcultureport.exception.ApiException;
import com.example.seoulcultureport.exception.ExceptionEnum;
import com.example.seoulcultureport.repository.BoardRepository;
import com.example.seoulcultureport.repository.ThumbsupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service @RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    private final ThumbsupRepository thumbsupRepository;

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
    public BoardDetailResponseDto getBoardDetailList(Long boardId, User user) {
        Board board = boardRepository.findById(boardId).orElseThrow(
                () -> new ApiException(ExceptionEnum.NOT_FOUND_POST_ALL)
        );
        return new BoardDetailResponseDto(board, user);
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

//    @Transactional
//    public ThumbsupResponseDto addThumbsup(Long boardId, User user) {
//        Board board = boardRepository.findById(boardId)
//                .orElseThrow(() -> new ApiException(ExceptionEnum.NOT_FOUND_POST_ALL));
//        Thumbsup thumbsupStatus = thumbsupRepository.findByBoardIdAndUserId(boardId, user.getId())
//                .orElse(null);
//        if(thumbsupStatus != null) {
//            throw new ApiException(ExceptionEnum.ALREADY_THUMBSUP);
//        }
//        Thumbsup thumbsup = new Thumbsup();
//        thumbsup.setBoard(board);
//        thumbsup.setUser(user);
//        thumbsup.setThumbsupStatus(ThumbsupStatus.ACTIVE);
//        board.addThumbsup(thumbsup);
//        Thumbsup savedThumbsup = thumbsupRepository.save(thumbsup);
//        return new ThumbsupResponseDto(StatusEnum.OK, savedThumbsup.getId(), savedThumbsup.getThumbsupStatus());
//    }

    @Transactional
    public ThumbsupResponseDto cancelThumbsup(Long boardId, Long thumbsId, User user) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new ApiException(ExceptionEnum.NOT_FOUND_POST_ALL));
        Thumbsup thumbsupStatus = thumbsupRepository.findById(thumbsId)
                .orElseThrow(() -> new ApiException(ExceptionEnum.NOT_FOUND_THUMBSUP));
        if(!thumbsupStatus.getUser().getId().equals(user.getId())) {
            throw new ApiException(ExceptionEnum.TOKEN_ERROR);
        }
        thumbsupStatus.setThumbsupStatus(ThumbsupStatus.CANCELED);
        board.cancelThumbsup(thumbsupStatus);
        Thumbsup deletedThumbsup = thumbsupRepository.save(thumbsupStatus);
        return new ThumbsupResponseDto(StatusEnum.OK, deletedThumbsup.getId(), deletedThumbsup.getThumbsupStatus());
    }

    @Transactional
    public ThumbsupResponseDto addThumbsup(Long boardId, User user) {

        Board board = boardRepository.findById(boardId).orElseThrow(
                () -> new ApiException(ExceptionEnum.NOT_FOUND_POST_ALL)
        );

        Optional<Thumbsup> getLike = thumbsupRepository.findByBoardIdAndUserId(boardId, user.getId());

        if (getLike.isEmpty()) {
            Thumbsup thumbsupSave = thumbsupRepository.save(new Thumbsup(user.getId(), board.getId(), true));
            return new ThumbsupResponseDto(StatusEnum.OK, thumbsupSave.getId(), thumbsupSave.isThumbsupStatus());
        } else {
            thumbsupRepository.deleteByBoardIdAndUserId(boardId, user.getId());
            return new ThumbsupResponseDto(StatusEnum.OK, null, false);
        }
    }
}