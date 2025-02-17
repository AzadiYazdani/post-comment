package com.haraji.baseinfo.service.location.state;


import com.haraji.baseinfo.model.location.City;
import com.haraji.baseinfo.model.location.State;
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
