package com.tecnotree.demo.mapper;


import com.tecnotree.demo.api.comment.dto.CommentRequestDto;
import com.tecnotree.demo.api.comment.dto.CommentResponseDto;
import com.tecnotree.demo.database.entity.CommentEntity;
import com.tecnotree.demo.model.Comment;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public abstract class CommentMapper {

    public abstract Comment toModel(CommentRequestDto requestDto);

    public abstract Comment toModel(CommentEntity entity);

    public abstract List<Comment> toModelList(List<CommentEntity> CommentEntityList);

    public abstract CommentEntity toEntity(Comment Comment);

    public abstract CommentResponseDto toDtoResponse(Comment entity);

    public abstract List<CommentResponseDto> toDtoResponseList(List<Comment> commentList);



}
