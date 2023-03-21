package com.example.seoulcultureport.service;

import com.example.seoulcultureport.dto.MessageResponseDto;
import com.example.seoulcultureport.dto.StatusEnum;
import com.example.seoulcultureport.dto.ThumbsupResponseDto;
import com.example.seoulcultureport.dto.ThumbsupStatus;
import com.example.seoulcultureport.dto.commentDto.CommentRequestDto;
import com.example.seoulcultureport.entity.*;
import com.example.seoulcultureport.exception.ApiException;
import com.example.seoulcultureport.exception.ExceptionEnum;
import com.example.seoulcultureport.repository.BoardRepository;
import com.example.seoulcultureport.repository.CommentLikeRepository;
import com.example.seoulcultureport.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;
    private final CommentLikeRepository commentLikeRepository;

    public MessageResponseDto createComment(Long id, CommentRequestDto req, User user) {
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new ApiException(ExceptionEnum.NOT_FOUND_POST_ALL)
        );
        Comment comment = commentRepository.saveAndFlush(new Comment(req, board, user));
        board.plusCmtCount();
        boardRepository.save(board);
        return new MessageResponseDto(StatusEnum.OK);
    }

    @Transactional
    public MessageResponseDto updateComment(Long commentId, CommentRequestDto commentRequestDto, User user) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new ApiException(ExceptionEnum.NOT_FOUND_COMMENT_ALL)
        );
        if (user.getId().equals(comment.getUserid())) {
            comment.update(commentRequestDto);
        } else {
            throw new ApiException(ExceptionEnum.NOT_FOUND_COMMENT);
        }
        return new MessageResponseDto(StatusEnum.OK);

    }

    @Transactional
    public MessageResponseDto deleteComment(Long commentId, User user) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new ApiException(ExceptionEnum.NOT_FOUND_COMMENT_ALL)
        );
        if (user.getId().equals(comment.getUserid())) {
            commentRepository.deleteById(commentId);
            Board board = comment.getBoard();
            board.minusCmtCount();
            boardRepository.save(board);
            return new MessageResponseDto(StatusEnum.OK);
        } else {
            throw new ApiException(ExceptionEnum.NOT_FOUND_COMMENT);
        }
    }

    public ThumbsupResponseDto addThumbsup(Long boardId, Long commentId, User user) {

        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new ApiException(ExceptionEnum.NOT_FOUND_COMMENT_ALL)
        );

        Optional<CommentLike> getLike = commentLikeRepository.findByBoardidAndCommentidAndUserid(user.getId(), boardId, commentId);

        if (getLike.isEmpty()) {
            CommentLike commentLike = commentLikeRepository.save(new CommentLike(user.getId(), boardId, commentId, ThumbsupStatus.ACTIVE));
        }
        return null;
    }

//    @Transactional
//    public ThumbsupResponseDto addThumbsup(Long boardId, User user) {
//
//        Board board = boardRepository.findById(boardId).orElseThrow(
//                () -> new ApiException(ExceptionEnum.NOT_FOUND_POST_ALL)
//        );
//
//        Optional<BoardLike> getLike = boardLikeRepository.findByBoardidAndUserid(boardId, user.getId());
//
//        if (getLike.isEmpty()) {
//            BoardLike boardLikeSave = boardLikeRepository.save(new BoardLike(user.getId(), board.getId(), ThumbsupStatus.ACTIVE));
//            return new ThumbsupResponseDto(StatusEnum.OK, boardLikeSave.getId(), boardLikeSave.getThumbsupStatus());
//        } else {
//            boardLikeRepository.deleteByBoardidAndUserid(boardId, user.getId());
//            return new ThumbsupResponseDto(StatusEnum.OK, null, ThumbsupStatus.CANCELED);
//        }
//    }

//    @Transactional
//    public ThumbsupResponseDto addThumbsup(Long commentId, User user) {
//        Comment comment = commentRepository.findById(commentId)
//                .orElseThrow(() -> new ApiException(ExceptionEnum.NOT_FOUND_COMMENT_ALL));
//        Thumbsup thumbsupStatus = thumbsupRepository.findByCommentIdAndUserId(commentId, user.getId())
//                .orElse(null);
//        if(thumbsupStatus != null) {
//            throw new ApiException(ExceptionEnum.ALREADY_THUMBSUP);
//        }
//        Thumbsup thumbsup = new Thumbsup();
//        thumbsup.setComment(comment);
//        thumbsup.setUser(user);
//        thumbsup.setThumbsupStatus(ThumbsupStatus.ACTIVE);
//        comment.addThumbsup(thumbsup);
//        Thumbsup savedThumbsup = thumbsupRepository.save(thumbsup);
//        return new ThumbsupResponseDto(StatusEnum.OK, savedThumbsup.getId(), savedThumbsup.getThumbsupStatus());
//    }
//
//    @Transactional
//    public ThumbsupResponseDto cancelThumbsup(Long commentId, Long thumbsId, User user) {
//        Comment comment = commentRepository.findById(commentId)
//                .orElseThrow(() -> new ApiException(ExceptionEnum.NOT_FOUND_COMMENT_ALL));
//        Thumbsup thumbsupStatus = thumbsupRepository.findById(thumbsId)
//                .orElseThrow(() -> new ApiException(ExceptionEnum.NOT_FOUND_THUMBSUP));
//        if(!thumbsupStatus.getUser().getId().equals(user.getId())) {
//            throw new ApiException(ExceptionEnum.TOKEN_ERROR);
//        }
//        thumbsupStatus.setThumbsupStatus(ThumbsupStatus.CANCELED);
//        comment.cancelThumbsup(thumbsupStatus);
//        Thumbsup deletedThumbsup = thumbsupRepository.save(thumbsupStatus);
//        return new ThumbsupResponseDto(StatusEnum.OK, deletedThumbsup.getId(), deletedThumbsup.getThumbsupStatus());
//    }
}
