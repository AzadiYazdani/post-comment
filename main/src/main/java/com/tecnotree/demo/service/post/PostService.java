package com.tecnotree.demo.service.post;


import com.tecnotree.demo.model.Comment;
import com.tecnotree.demo.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostService {

    Post getById(long id);

    Page<Post> getAllByPaging(Pageable pageable);

    List<Comment> getAllCommentsById(long postId);

    List<Post> searchTitle(String title);

    Post newPost(Post post);

    Post updatePost(long id, Post post);

    void deletePost(long id);
}
