package com.haraji.sale.api.comment;


import com.haraji.sale.api.ResponseDto;
import com.haraji.sale.api.comment.dto.CommentRequestDto;
import com.haraji.sale.api.comment.dto.CommentResponseDto;
import com.haraji.sale.api.comment.dto.CommentUpdateRequestDto;
import com.haraji.sale.api.common.PageRequestDto;
import com.haraji.sale.mapper.CommentMapper;
import com.haraji.sale.model.Comment;
import com.haraji.sale.model.CommentUpdateRequest;
import com.haraji.sale.service.comment.CommentService;
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


    @GetMapping()
    @ApiOperation(value = "Get all comments with paging")
    public ResponseEntity<ResponseDto<Page<CommentResponseDto>>> getAllByPaging(@RequestBody @Valid @NotNull PageRequestDto pageRequestDto) {
        log.debug("received pageRequestDto for retrieving all comment is {}", pageRequestDto);
        Pageable pageable = PageRequest.of(pageRequestDto.getPage(), pageRequestDto.getSize());
        Page<Comment> commentPage = commentService.getAllByPaging(pageable);
        log.info("the commentPage for sending is {}", commentPage);
        return new ResponseEntity<ResponseDto<Page<CommentResponseDto>>>(ResponseDto.success(commentPage), HttpStatus.OK);
    }

    @GetMapping(value = "/{commentId}")
    @ApiOperation(value = "Find a comment with given id")
    public ResponseEntity<ResponseDto<CommentResponseDto>> getById(@PathVariable("commentId") @Valid @Min(1) long commentId) {
        log.debug("received commentId for retrieving Comment is {}", commentId);
        Comment comment = commentService.getById(commentId);
        CommentResponseDto dtoResponse = commentMapper.toDtoResponse(comment);
        log.info("the PostDto for sending is {}", dtoResponse);
        return new ResponseEntity<ResponseDto<CommentResponseDto>>(ResponseDto.success(dtoResponse), HttpStatus.OK);
    }

    @PostMapping()
    @ApiOperation(value = "Add new comment for a user")
    public ResponseEntity<ResponseDto<CommentResponseDto>> newComment(@RequestBody @Valid @NotNull CommentRequestDto requestDto) {
        log.debug("received CommentRequestDto for retrieving Comment is {}", requestDto);
        Comment comment = commentMapper.toModel(requestDto);
        comment = commentService.newComment(comment);
        CommentResponseDto dtoResponse = commentMapper.toDtoResponse(comment);
        log.info("the PostDto for sending is {}", dtoResponse);
        return new ResponseEntity<ResponseDto<CommentResponseDto>>(ResponseDto.success(dtoResponse), HttpStatus.OK);
    }

    @PatchMapping("/{commentId}")
    @ApiOperation(value = "Update title or body of a comment")
    public ResponseEntity<ResponseDto<CommentResponseDto>> updateComment(@PathVariable("commentId") @Valid @Min(1)  long commentId, @RequestBody @Valid @NotNull CommentUpdateRequestDto requestDto) {
        log.debug("received CommentRequestDto for retrieving Comment is {}", requestDto);
        CommentUpdateRequest commentUpdateRequest = commentMapper.toModel(requestDto);
        Comment comment = commentService.updateComment(commentId, commentUpdateRequest);
        CommentResponseDto dtoResponse = commentMapper.toDtoResponse(comment);
        log.info("the PostDto for sending is {}", dtoResponse);
        return new ResponseEntity<ResponseDto<CommentResponseDto>>(ResponseDto.success(dtoResponse), HttpStatus.OK);
    }

    @DeleteMapping("/{commentId}")
    @ApiOperation(value = "Delete a comment with a given id")
    public ResponseEntity<ResponseDto> deleteComment(@PathVariable("commentId") @Valid @Min(1) long commentId) {
        log.debug("received commentId for retrieving Comment is {}", commentId);
        commentService.deleteComment(commentId);
        log.info("the comment was deleted successfully with id {}", commentId);
        return new ResponseEntity<>(ResponseDto.success(commentId), HttpStatus.OK);
    }

}
