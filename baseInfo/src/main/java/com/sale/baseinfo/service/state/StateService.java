package com.sale.baseinfo.service.state;


import com.sale.baseinfo.model.City;
import com.sale.baseinfo.model.State;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Validated
public interface StateService {

    State getById(@Min(1) int id);

    List<State> getAll();

    Page<State> getAllByPaging(@Valid @NotNull Pageable pageable);

    List<City> getAllCitiesById(@Min(1) int stateId);

    List<State> searchTitle(@NotNull String title);

}
