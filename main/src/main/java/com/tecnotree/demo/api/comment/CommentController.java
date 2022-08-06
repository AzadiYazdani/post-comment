package com.tecnotree.demo.api.comment;


import com.tecnotree.demo.api.ResponseDto;
import com.tecnotree.demo.api.comment.dto.CommentRequestDto;
import com.tecnotree.demo.api.comment.dto.CommentResponseDto;
import com.tecnotree.demo.api.common.PageRequestDto;
import com.tecnotree.demo.mapper.CommentMapper;
import com.tecnotree.demo.model.Comment;
import com.tecnotree.demo.service.comment.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@RestController
@Api(value = "Comment operations")
@RequestMapping(value = "/comments")
@Slf4j
@Validated
public class CommentController {

    private final CommentService commentService;
    private final CommentMapper commentMapper;

    public CommentController(CommentService commentService, CommentMapper commentMapper) {
        this.commentService = commentService;
        this.commentMapper = commentMapper;
    }


    @GetMapping("/comments")
    @ApiOperation(value = "Get all comments with paging")
    public ResponseEntity<ResponseDto<Page<CommentResponseDto>>> getAllByPaging(@RequestBody @Valid @NotNull PageRequestDto pageRequestDto) {
        log.debug("received pageRequestDto for retrieving all comment is {}", pageRequestDto);
        Pageable pageable = PageRequest.of(pageRequestDto.getPage(), pageRequestDto.getSize());
        Page<Comment> commentPage = commentService.getAllByPaging(pageable);
        log.info("the commentPage for sending is {}", commentPage);
        return new ResponseEntity<ResponseDto<Page<CommentResponseDto>>>(ResponseDto.success(commentPage), HttpStatus.OK);
    }

    @GetMapping(value = "/comments/{commentId}")
    @ApiOperation(value = "Find a comment with given id")
    public ResponseEntity<ResponseDto<CommentResponseDto>> getById(@PathVariable("commentId") @Valid @Min(1) long commentId) {
        log.debug("received commentId for retrieving Comment is {}", commentId);
        Comment comment = commentService.getById(commentId);
        CommentResponseDto dtoResponse = commentMapper.toDtoResponse(comment);
        log.info("the PostDto for sending is {}", dtoResponse);
        return new ResponseEntity<ResponseDto<CommentResponseDto>>(ResponseDto.success(dtoResponse), HttpStatus.OK);
    }

    @PostMapping("/comments")
    @ApiOperation(value = "Add new comment for a user")
    public ResponseEntity<ResponseDto<CommentResponseDto>> newComment(@RequestBody @Valid @NotNull CommentRequestDto requestDto) {
        log.debug("received CommentRequestDto for retrieving Comment is {}", requestDto);
        Comment comment = commentMapper.toModel(requestDto);
        comment = commentService.newComment(comment);
        CommentResponseDto dtoResponse = commentMapper.toDtoResponse(comment);
        log.info("the PostDto for sending is {}", dtoResponse);
        return new ResponseEntity<ResponseDto<CommentResponseDto>>(ResponseDto.success(dtoResponse), HttpStatus.OK);
    }

    @PatchMapping("/comments/{commentId}")
    @ApiOperation(value = "Update title or body of a comment")
    public ResponseEntity<ResponseDto<CommentResponseDto>> updateComment(@PathVariable("commentId") @Valid @Min(1)  long commentId, @RequestBody @Valid @NotNull  CommentRequestDto requestDto) {
        log.debug("received CommentRequestDto for retrieving Comment is {}", requestDto);
        Comment comment = commentMapper.toModel(requestDto);
        comment = commentService.updateComment(commentId, comment);
        CommentResponseDto dtoResponse = commentMapper.toDtoResponse(comment);
        log.info("the PostDto for sending is {}", dtoResponse);
        return new ResponseEntity<ResponseDto<CommentResponseDto>>(ResponseDto.success(dtoResponse), HttpStatus.OK);
    }

    @DeleteMapping("/comments/{commentId}")
    @ApiOperation(value = "Delete a comment with a given id")
    public ResponseEntity<ResponseDto> deleteComment(@PathVariable("commentId") @Valid @Min(1) long commentId) {
        log.debug("received commentId for retrieving Comment is {}", commentId);
        commentService.deleteComment(commentId);
        log.info("the comment was deleted successfully with id {}", commentId);
        return new ResponseEntity<>(ResponseDto.success(commentId), HttpStatus.OK);
    }

}
