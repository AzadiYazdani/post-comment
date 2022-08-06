package com.tecnotree.demo.api.post;


import com.tecnotree.demo.api.ResponseDto;
import com.tecnotree.demo.api.comment.dto.CommentResponseDto;
import com.tecnotree.demo.api.post.dto.PageRequestDto;
import com.tecnotree.demo.api.post.dto.PostRequestDto;
import com.tecnotree.demo.api.post.dto.PostResponseDto;
import com.tecnotree.demo.mapper.CommentMapper;
import com.tecnotree.demo.mapper.PostMapper;
import com.tecnotree.demo.model.Comment;
import com.tecnotree.demo.model.Post;
import com.tecnotree.demo.service.post.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping(value = "/posts")
@Slf4j
public class PostController {

    private final PostService postService;
    private final PostMapper postMapper;
    private final CommentMapper commentMapper;

    public PostController(PostService postService, PostMapper postMapper, CommentMapper commentMapper) {
        this.postService = postService;
        this.postMapper = postMapper;
        this.commentMapper = commentMapper;
    }

    @GetMapping("?")
    public ResponseEntity<ResponseDto<Page<PostResponseDto>>> getAllByPaging(@RequestBody @NotNull PageRequestDto pageRequestDto) {
        log.debug("received pageRequestDto for retrieving all post is {}", pageRequestDto);
        Pageable pageable = PageRequest.of(pageRequestDto.getPage(), pageRequestDto.getSize());
        Page<Post> postPage = postService.getAllByPaging(pageable);
        log.info("the postPage for sending is {}", postPage);
        return new ResponseEntity<ResponseDto<Page<PostResponseDto>>>(ResponseDto.success(postPage), HttpStatus.OK);
    }

    @GetMapping(value = "/{postId}")
    public ResponseEntity<ResponseDto<PostResponseDto>> getById(@PathVariable("postId") long postId) {
        log.debug("received postId for retrieving Post is {}", postId);
        Post post = postService.getById(postId);
        PostResponseDto dtoResponse = postMapper.toDtoResponse(post);
        log.info("the PostDto for sending is {}", dtoResponse);
        return new ResponseEntity<ResponseDto<PostResponseDto>>(ResponseDto.success(dtoResponse), HttpStatus.OK);
    }


    @GetMapping("/{postId}/comments")
    public ResponseEntity<ResponseDto<PostResponseDto>> getAllComments(@PathVariable("postId") long postId) {
        log.debug("received postId for retrieving comments is {}", postId);
        List<Comment> comments = postService.getAllCommentsById(postId);
        List<CommentResponseDto> commentResponseDtoList = commentMapper.toDtoResponseList(comments);
        log.info("the list of comments for sending is {}", commentResponseDtoList);
        return new ResponseEntity<ResponseDto<PostResponseDto>>(ResponseDto.success(commentResponseDtoList), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<ResponseDto<List<PostResponseDto>>> search(@RequestParam("title") String value) {
        log.debug("received value for searching title is {}", value);
        List<Post> postList = postService.searchTitle(value);
        List<PostResponseDto> postResponseDtoList = postMapper.toDtoResponseList(postList);
        log.info("the list of post for sending is {}", postResponseDtoList);
        return new ResponseEntity<ResponseDto<List<PostResponseDto>>>(ResponseDto.success(postResponseDtoList), HttpStatus.OK);
    }


    @PostMapping()
    public ResponseEntity<ResponseDto<PostResponseDto>> newPost(@RequestBody @NotNull PostRequestDto requestDto) {
        log.debug("received PostRequestDto for retrieving Post is {}", requestDto);
        Post post = postMapper.toModel(requestDto);
        post = postService.newPost(post);
        PostResponseDto dtoResponse = postMapper.toDtoResponse(post);
        log.info("the PostDto for sending is {}", dtoResponse);
        return new ResponseEntity<ResponseDto<PostResponseDto>>(ResponseDto.success(dtoResponse), HttpStatus.OK);
    }

    @PatchMapping("/{postId}")
    public ResponseEntity<ResponseDto<PostResponseDto>> updatePost(@PathVariable("postId") long postId, @RequestBody @NotNull PostRequestDto requestDto) {
        log.debug("received PostRequestDto for retrieving Post is {}", requestDto);
        Post post = postMapper.toModel(requestDto);
        post = postService.updatePost(postId, post);
        PostResponseDto dtoResponse = postMapper.toDtoResponse(post);
        log.info("the PostDto for sending is {}", dtoResponse);
        return new ResponseEntity<ResponseDto<PostResponseDto>>(ResponseDto.success(dtoResponse), HttpStatus.OK);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<ResponseDto> deletePost(@PathVariable("postId") long postId) {
        log.debug("received postId for retrieving Post is {}", postId);
        postService.deletePost(postId);
        log.info("the post was deleted successfully with id {}", postId);
        return new ResponseEntity<>(ResponseDto.success(postId), HttpStatus.OK);
    }

}
