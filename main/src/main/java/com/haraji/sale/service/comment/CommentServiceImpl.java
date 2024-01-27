package com.haraji.sale.service.comment;


import com.haraji.sale.database.entity.CommentEntity;
import com.haraji.sale.database.entity.PostEntity;
import com.haraji.sale.database.repository.CommentRepository;
import com.haraji.sale.error.exception.CommentNotFoundException;
import com.haraji.sale.error.exception.PersistException;
import com.haraji.sale.mapper.CommentMapper;
import com.haraji.sale.model.Comment;
import com.haraji.sale.model.CommentUpdateRequest;
import lombok.extern.slf4j.Slf4j;
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
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;

    public CommentServiceImpl(CommentRepository commentRepository, CommentMapper commentMapper) {
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
    }


    @Override
    public Comment getById(@Min(1) long id) {
        try {
            CommentEntity commentEntity = commentRepository.findByIdAndDeletedIsFalse(id)
                    .orElseThrow(() -> new CommentNotFoundException(id));
            return commentMapper.toModel(commentEntity);
        } catch (Exception e) {
            log.info("\nThe exception '{}' was thrown for fetching comment with id {}", e.getMessage(), id);
            throw new CommentNotFoundException(id);
        }
    }

    @Override
    public Page<Comment> getAllByPaging(@Valid @NotNull Pageable pageable) {
        try {
            Page<CommentEntity> commentEntityPage = commentRepository.findAll(pageable);

            if (commentEntityPage.isEmpty()) {
                throw new CommentNotFoundException();
            }
            List<Comment> commentList = new ArrayList<>();
            commentEntityPage.forEach(commentEntity -> commentList.add(commentMapper.toModel(commentEntity)));
            return new PageImpl<>(commentList);
        } catch (Exception e) {
            log.info("\nThe exception '{}' was thrown for fetching all comments ", e.getMessage());
            throw new CommentNotFoundException();
        }
    }

    @Override
    public Comment newComment(@Valid @NotNull Comment comment) {
        try {
            CommentEntity commentEntity = commentMapper.toNewEntity(comment);

            PostEntity postEntity = PostEntity.newInstance().setId(comment.getPostId()).build();
            commentEntity.setPost(postEntity);

            commentRepository.save(commentEntity);
            log.info("\n New comment with id {} saved", commentEntity.getId());
            return commentMapper.toModel(commentEntity);
        } catch (Exception e) {
            log.info("\nThe exception '{}' was thrown for saving comment {}", e.getMessage(), comment);
            throw new PersistException(comment);
        }
    }

    @Override
    public Comment updateComment(@Min(1) long commentId, @Valid @NotNull CommentUpdateRequest commentUpdateRequest) {
        try {
            CommentEntity commentEntity = commentRepository.findByIdAndDeletedIsFalse(commentId)
                    .orElseThrow(() -> new CommentNotFoundException(commentId));

            updateEntity(commentEntity, commentUpdateRequest);
            commentRepository.save(commentEntity);
            log.info("\n comment with id {} updated", commentId);
            return commentMapper.toModel(commentEntity);
        }catch (CommentNotFoundException e){
            log.info("\nThe CommentsNotFoundException was thrown for updating comment with id {}", commentId);
            throw e;
        } catch (Exception e) {
            log.info("\nThe exception '{}' was thrown for updating comment with id {}", e.getMessage(), commentId);
            throw new PersistException(commentId);
        }
    }

    @Override
    public void deleteComment(@Min(1) long commentId) {
        try {
            CommentEntity commentEntity = commentRepository.findByIdAndDeletedIsFalse(commentId)
                    .orElseThrow(() -> new CommentNotFoundException(commentId));
            commentEntity.setDeleted(true);
            commentRepository.save(commentEntity);
            log.info("\ncomment with id {} deleted", commentId);
        }catch (CommentNotFoundException e){
            log.info("\nThe CommentNotFoundException was thrown for deleting comment with id {}", commentId);
            throw e;
        } catch (Exception e) {
            log.info("\nThe exception '{}' was thrown for deleting comment with id {}", e.getMessage(), commentId);
            throw new PersistException(commentId);
        }
    }

    private void updateEntity(CommentEntity commentEntity, CommentUpdateRequest commentUpdateRequest) {
        commentEntity.setBody(commentUpdateRequest.getBody());
        commentEntity.setUpdateTime(LocalDateTime.now());
    }

}
