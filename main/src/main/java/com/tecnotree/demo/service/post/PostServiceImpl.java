package com.tecnotree.demo.service.post;


import com.tecnotree.demo.database.entity.CommentEntity;
import com.tecnotree.demo.database.entity.PostEntity;
import com.tecnotree.demo.database.entity.UserEntity;
import com.tecnotree.demo.database.repository.PostRepository;
import com.tecnotree.demo.error.exception.BadRequestException;
import com.tecnotree.demo.error.exception.CommentNotFoundException;
import com.tecnotree.demo.error.exception.PersistException;
import com.tecnotree.demo.error.exception.PostNotFoundException;
import com.tecnotree.demo.mapper.CommentMapper;
import com.tecnotree.demo.mapper.PostMapper;
import com.tecnotree.demo.model.Comment;
import com.tecnotree.demo.model.Post;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@Validated
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final PostMapper postMapper;
    private final CommentMapper commentMapper;

    public PostServiceImpl(PostRepository postRepository, PostMapper postMapper, CommentMapper commentMapper) {
        this.postRepository = postRepository;
        this.postMapper = postMapper;
        this.commentMapper = commentMapper;
    }


    @Override
    public Post getById(@Min(1) long id) {
        try {
            PostEntity postEntity = postRepository.findByIdAndDeletedIsFalse(id)
                    .orElseThrow(() -> new PostNotFoundException(id));
            return postMapper.toModel(postEntity);
        } catch (Exception e) {
            log.info("\nThe exception '{}' was thrown for fetching post with id {}", e.getMessage(), id);
            throw new PostNotFoundException(id);
        }
    }


    @Override
    public Page<Post> getAllByPaging(@Valid @NotNull Pageable pageable) {
        try {
            Page<PostEntity> postEntityPage = postRepository.findAll(pageable);

            if (postEntityPage.isEmpty()) {
                throw new PostNotFoundException();
            }
            List<Post> postList = new ArrayList<>();
            postEntityPage.forEach(p -> postList.add(postMapper.toModel(p)));
            return new PageImpl<>(postList);
        } catch (Exception e) {
            log.info("\nThe exception '{}' was thrown for fetching all posts ", e.getMessage());
            throw new PostNotFoundException();
        }
    }


    @Override
    public List<Comment> getAllCommentsById(@Min(1) long postId) {
        try {
            PostEntity postEntity = postRepository.findByIdAndDeletedIsFalse(postId)
                    .orElseThrow(() -> new PostNotFoundException(postId));
            List<CommentEntity> commentEntities = postEntity.getComments();

            return commentMapper.toModelList(commentEntities);
        } catch (Exception e) {
            log.info("\nThe exception '{}' was thrown for fetching comments of the post with id {}", e.getMessage(), postId);
            throw new CommentNotFoundException(postId);
        }
    }

    @Override
    public List<Post> searchTitle(@NotNull String titleValue) {
        if (StringUtils.isBlank(titleValue))
            throw new BadRequestException();

        List<PostEntity> postEntityList = postRepository.findAllByTitleContainsAndDeletedIsFalse(titleValue);
        return postMapper.toModelList(postEntityList);
    }


    @Override
    public Post newPost(@Valid @NotNull Post post) {
        try {
            PostEntity postEntity = postMapper.toNewEntity(post);

            UserEntity userEntity = UserEntity.newInstance().setId(post.getUserId()).build();
            postEntity.setUser(userEntity);

            postRepository.save(postEntity);
            return postMapper.toModel(postEntity);
        } catch (Exception e) {
            log.info("\nThe exception '{}' was thrown for saving post {}", e.getMessage(), post);
            throw new PersistException(post);
        }
    }

    @Override
    public Post updatePost(@Min(1) long postId, @Valid @NotNull Post post) {
        try {
            PostEntity postEntity = postRepository.findByIdAndDeletedIsFalse(postId)
                    .orElseThrow(() -> new PostNotFoundException(postId));

            updateEntity(postEntity, post);
            postRepository.save(postEntity);
            return postMapper.toModel(postEntity);
        }catch (PostNotFoundException e){
            log.info("\nThe PostNotFoundException was thrown for updating post with id {}", postId);
            throw e;
        } catch (Exception e) {
            log.info("\nThe exception '{}' was thrown for updating post with id {}", e.getMessage(), postId);
            throw new PersistException(postId);
        }
    }


    @Override
    public void deletePost(@Min(1) long postId) {
        try {
            PostEntity postEntity = postRepository.findByIdAndDeletedIsFalse(postId)
                    .orElseThrow(() -> new PostNotFoundException(postId));
            postEntity.setDeleted(true);
            postRepository.save(postEntity);
        }catch (PostNotFoundException e){
            log.info("\nThe PostNotFoundException was thrown for deleting post with id {}", postId);
            throw e;
        } catch (Exception e) {
            log.info("\nThe exception '{}' was thrown for deleting post with id {}", e.getMessage(), postId);
            throw new PersistException(postId);
        }
    }

    private void updateEntity(PostEntity postEntity, Post post) {
        postEntity.setTitle(post.getTitle());
        postEntity.setBody(post.getBody());
        postEntity.setUpdateTime(LocalDateTime.now());
    }

}
