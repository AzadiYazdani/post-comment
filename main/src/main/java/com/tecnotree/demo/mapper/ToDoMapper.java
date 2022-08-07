package com.tecnotree.demo.mapper;


import com.tecnotree.demo.api.todo.dto.ToDoRequestDto;
import com.tecnotree.demo.api.todo.dto.ToDoResponseDto;
import com.tecnotree.demo.database.entity.ToDoEntity;
import com.tecnotree.demo.model.ToDo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public abstract class ToDoMapper {

    @Mapping(target = "id", ignore = true)
    public abstract ToDo toModel(ToDoRequestDto requestDto);

    @Mapping(target = "userId", source = "entity.user.id")
    public abstract ToDo toModel(ToDoEntity entity);

    public abstract List<ToDo> toModelList(List<ToDoEntity> postEntityList);

    public abstract ToDoResponseDto toDtoResponse(ToDo toDo);

    public abstract List<ToDoResponseDto> toDtoResponseList(List<ToDo> postList);



}
