package com.tecnotree.demo.service.comment;


import com.tecnotree.demo.database.entity.CommentEntity;
import com.tecnotree.demo.database.repository.CommentRepository;
import com.tecnotree.demo.model.Comment;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }


    @Override
    public Comment getById(long id){
        Optional<CommentEntity> commentEntity = commentRepository.findById(id);

        return null;
    }

    @Override
    public List<Comment> getAll(){
        return null;
    }


    @Override
    public Comment save(Comment post) {
        return null;
    }

    @Override
    public Comment update(long id, Comment post) {
        return null;
    }

    @Override
    public Comment delete(long id) {
        return null;
    }


}
