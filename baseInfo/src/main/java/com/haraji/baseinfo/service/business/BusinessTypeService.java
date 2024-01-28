package com.haraji.baseinfo.service.business;


import com.haraji.baseinfo.model.BusinessType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Validated
public interface BusinessTypeService {

    BusinessType getById(@Min(1) long id);

    Page<BusinessType> getAllByPaging(@Valid @NotNull Pageable pageable);
    
    List<BusinessType> getAll();

    List<BusinessType> searchTitle(@NotNull String titleValue);

}
