package com.tecnotree.demo.service.comment;


import com.tecnotree.demo.model.Comment;
import com.tecnotree.demo.model.Comment;

import java.util.List;

public interface CommentService {

    Comment getById(long id);

    List<Comment> getAll();

    Comment save(Comment post);

    Comment update(long id, Comment post);

    Comment delete(long id);
}
