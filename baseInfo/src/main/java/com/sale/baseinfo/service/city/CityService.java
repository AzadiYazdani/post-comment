package com.sale.baseinfo.service.city;


import com.sale.baseinfo.model.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public interface CityService {

    City getById(@Min(1) int id);

    Page<City> getAllByPaging(@Valid @NotNull Pageable pageable);

}
