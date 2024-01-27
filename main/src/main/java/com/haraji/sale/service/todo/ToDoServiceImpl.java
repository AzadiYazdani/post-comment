package com.haraji.sale.service.todo;


import com.haraji.sale.database.entity.ToDoEntity;
import com.haraji.sale.database.repository.ToDoRepository;
import com.haraji.sale.error.exception.ToDoNotFoundException;
import com.haraji.sale.mapper.ToDoMapper;
import com.haraji.sale.model.ToDo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@Validated
public class ToDoServiceImpl implements ToDoService {

    private final ToDoRepository toDoRepository;
    private final ToDoMapper toDoMapper;

    public ToDoServiceImpl(ToDoRepository toDoRepository, ToDoMapper toDoMapper) {
        this.toDoRepository = toDoRepository;
        this.toDoMapper = toDoMapper;
    }


    @Override
    public Page<ToDo> getAllByPaging(@Valid @NotNull Pageable pageable) {
        try {
            Page<ToDoEntity> toDoEntityPage = toDoRepository.findAll(pageable);

            if (toDoEntityPage.isEmpty()) {
                throw new ToDoNotFoundException();
            }
            List<ToDo> toDoList = new ArrayList<>();
            toDoEntityPage.forEach(p -> toDoList.add(toDoMapper.toModel(p)));
            return new PageImpl<>(toDoList);
        } catch (Exception e) {
            log.info("\nThe exception '{}' was thrown for fetching all todos ", e.getMessage());
            throw new ToDoNotFoundException();
        }
    }

    @Override
    public List<ToDo> getAllByUser(@Min(1) long userId, boolean completed) {
        try {
            List<ToDoEntity> toDoEntityList = toDoRepository.findAllByUserIdAndAndCompleted(userId, completed);

            if (toDoEntityList.isEmpty()) {
                throw new ToDoNotFoundException();
            }
            return toDoMapper.toModelList(toDoEntityList);
        } catch (Exception e) {
            log.info("\nThe exception '{}' was thrown for fetching all todos ", e.getMessage());
            throw new ToDoNotFoundException();
        }
    }

}
