package com.tecnotree.demo.api.post;


import com.tecnotree.demo.api.ResponseDto;
import com.tecnotree.demo.api.comment.dto.CommentResponseDto;
import com.tecnotree.demo.api.common.PageRequestDto;
import com.tecnotree.demo.api.post.dto.PostRequestDto;
import com.tecnotree.demo.api.post.dto.PostResponseDto;
import com.tecnotree.demo.mapper.CommentMapper;
import com.tecnotree.demo.mapper.PostMapper;
import com.tecnotree.demo.model.Comment;
import com.tecnotree.demo.model.Post;
import com.tecnotree.demo.service.post.PostService;
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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@Api(value = "Post operations")
@Slf4j
@Validated
public class PostController {

    private final PostService postService;
    private final PostMapper postMapper;
    private final CommentMapper commentMapper;

    public PostController(PostService postService, PostMapper postMapper, CommentMapper commentMapper) {
        this.postService = postService;
        this.postMapper = postMapper;
        this.commentMapper = commentMapper;
    }

    @GetMapping("/posts")
    @ApiOperation(value = "Get all posts with paging")
    public ResponseEntity<ResponseDto<Page<PostResponseDto>>> getAllByPaging(@RequestBody @Valid @NotNull PageRequestDto pageRequestDto) {
        log.debug("received pageRequestDto for retrieving all post is {}", pageRequestDto);
        Pageable pageable = PageRequest.of(pageRequestDto.getPage(), pageRequestDto.getSize());
        Page<Post> postPage = postService.getAllByPaging(pageable);
        log.info("the postPage for sending is {}", postPage);
        return new ResponseEntity<ResponseDto<Page<PostResponseDto>>>(ResponseDto.success(postPage), HttpStatus.OK);
    }

    @GetMapping(value = "/posts/{postId}")
    @ApiOperation(value = "Find a post with given id")
    public ResponseEntity<ResponseDto<PostResponseDto>> getById(@PathVariable("postId") @Valid @Min(1) long postId) {
        log.debug("received postId for retrieving Post is {}", postId);
        Post post = postService.getById(postId);
        PostResponseDto dtoResponse = postMapper.toDtoResponse(post);
        log.info("the PostDto for sending is {}", dtoResponse);
        return new ResponseEntity<ResponseDto<PostResponseDto>>(ResponseDto.success(dtoResponse), HttpStatus.OK);
    }


    @GetMapping("/posts/{postId}/comments")
    @ApiOperation(value = "Find all comments of a post with given id of post")
    public ResponseEntity<ResponseDto<PostResponseDto>> getAllComments(@PathVariable("postId") @Valid @Min(1) long postId) {
        log.debug("received postId for retrieving comments is {}", postId);
        List<Comment> comments = postService.getAllCommentsById(postId);
        List<CommentResponseDto> commentResponseDtoList = commentMapper.toDtoResponseList(comments);
        log.info("the list of comments for sending is {}", commentResponseDtoList);
        return new ResponseEntity<ResponseDto<PostResponseDto>>(ResponseDto.success(commentResponseDtoList), HttpStatus.OK);
    }

    @GetMapping("/posts?")
    @ApiOperation(value = "Find all posts with given part of title")
    public ResponseEntity<ResponseDto<List<PostResponseDto>>> search(@RequestParam("title") @Valid @NotNull String value) {
        log.debug("received value for searching title is {}", value);
        List<Post> postList = postService.searchTitle(value);
        List<PostResponseDto> postResponseDtoList = postMapper.toDtoResponseList(postList);
        log.info("the list of post for sending is {}", postResponseDtoList);
        return new ResponseEntity<ResponseDto<List<PostResponseDto>>>(ResponseDto.success(postResponseDtoList), HttpStatus.OK);
    }


    @PostMapping("/posts")
    @ApiOperation(value = "Add new post for a user")
    public ResponseEntity<ResponseDto<PostResponseDto>> newPost(@RequestBody @Valid @NotNull PostRequestDto requestDto) {
        log.debug("received PostRequestDto for retrieving Post is {}", requestDto);
        Post post = postMapper.toModel(requestDto);
        post = postService.newPost(post);
        PostResponseDto dtoResponse = postMapper.toDtoResponse(post);
        log.info("the PostDto for sending is {}", dtoResponse);
        return new ResponseEntity<ResponseDto<PostResponseDto>>(ResponseDto.success(dtoResponse), HttpStatus.OK);
    }

    @PatchMapping("/posts/{postId}")
    @ApiOperation(value = "Update title or body of a post")
    public ResponseEntity<ResponseDto<PostResponseDto>> updatePost(@PathVariable("postId") @Valid @Min(1) long postId, @RequestBody @Valid @NotNull PostRequestDto requestDto) {
        log.debug("received PostRequestDto for retrieving Post is {}", requestDto);
        Post post = postMapper.toModel(requestDto);
        post = postService.updatePost(postId, post);
        PostResponseDto dtoResponse = postMapper.toDtoResponse(post);
        log.info("the PostDto for sending is {}", dtoResponse);
        return new ResponseEntity<ResponseDto<PostResponseDto>>(ResponseDto.success(dtoResponse), HttpStatus.OK);
    }

    @DeleteMapping("/posts/{postId}")
    @ApiOperation(value = "Delete a post with a given id")
    public ResponseEntity<ResponseDto> deletePost(@PathVariable("postId") @Valid @Min(1) long postId) {
        log.debug("received postId for retrieving Post is {}", postId);
        postService.deletePost(postId);
        log.info("the post was deleted successfully with id {}", postId);
        return new ResponseEntity<>(ResponseDto.success(postId), HttpStatus.OK);
    }

}
