package com.tecnotree.demo.service.comment;


import com.tecnotree.demo.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Validated
public interface CommentService {

    Comment getById(@Min(1) long id);

    Page<Comment> getAllByPaging(@Valid @NotNull Pageable pageable);

    Comment newComment(@Valid @NotNull Comment comment);

    Comment updateComment(@Min(1) long id, @Valid @NotNull Comment comment);

    void deleteComment(@Min(1) long id);
}
