package com.haraji.sale.service.todo;


import com.haraji.sale.model.ToDo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Validated
public interface ToDoService {

    Page<ToDo> getAllByPaging(@Valid @NotNull Pageable pageable);

    List<ToDo> getAllByUser(@Min(1) long userId, boolean completed);

}
