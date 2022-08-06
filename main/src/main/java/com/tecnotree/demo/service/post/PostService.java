package com.tecnotree.demo.service.post;


import com.tecnotree.demo.model.Comment;
import com.tecnotree.demo.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Validated
public interface PostService {

    Post getById(@Min(1) long id);

    Page<Post> getAllByPaging(@Valid @NotNull Pageable pageable);

    List<Comment> getAllCommentsById(@Min(1) long postId);

    List<Post> searchTitle(@NotNull String title);

    Post newPost(@Valid @NotNull Post post);

    Post updatePost(@Min(1) long id, @Valid @NotNull Post post);

    void deletePost(@Min(1) long id);
}
