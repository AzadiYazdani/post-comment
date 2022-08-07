package com.tecnotree.demo.api.todo;


import com.tecnotree.demo.api.ResponseDto;
import com.tecnotree.demo.api.common.PageRequestDto;
import com.tecnotree.demo.api.todo.dto.ToDoResponseDto;
import com.tecnotree.demo.mapper.ToDoMapper;
import com.tecnotree.demo.model.ToDo;
import com.tecnotree.demo.service.todo.ToDoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@Api(value = "todo operations")
@RequestMapping(value = "/todos")
@Slf4j
@Validated
public class ToDoController {

    private final ToDoService toDoService;
    private final ToDoMapper toDoMapper;

    public ToDoController(ToDoService toDoService, ToDoMapper toDoMapper) {
        this.toDoService = toDoService;
        this.toDoMapper = toDoMapper;
    }


    @GetMapping("/")
    @ApiOperation(value = "Get all todos with paging")
    public ResponseEntity<ResponseDto<Page<ToDoResponseDto>>> getAllByPaging(@RequestBody @Valid @NotNull PageRequestDto pageRequestDto) {
        log.debug("received pageRequestDto for retrieving all toDo is {}", pageRequestDto);
        Pageable pageable = PageRequest.of(pageRequestDto.getPage(), pageRequestDto.getSize());
        Page<ToDo> toDoPage = toDoService.getAllByPaging(pageable);
        log.info("the toDoPage for sending is {}", toDoPage);
        return new ResponseEntity<ResponseDto<Page<ToDoResponseDto>>>(ResponseDto.success(toDoPage), HttpStatus.OK);
    }

    @GetMapping
    @ApiOperation(value = "Find a toDo with given userId and completed value")
    public ResponseEntity<ResponseDto<ToDoResponseDto>> getByUserId(@RequestParam("userId") @Valid long userId, @RequestParam("completed") @Valid boolean completed) {
        log.debug("received userId for retrieving ToDo is {} and completed is {}", userId, completed);
        List<ToDo> toDo = toDoService.getAllByUser(userId, completed);
        List<ToDoResponseDto> dtoResponse = toDoMapper.toDtoResponseList(toDo);
        log.info("the PostDto for sending is {}", dtoResponse);
        return new ResponseEntity<ResponseDto<ToDoResponseDto>>(ResponseDto.success(dtoResponse), HttpStatus.OK);
    }

}
