package com.sale.baseinfo.mapper;


import com.sale.baseinfo.api.businessType.BusinessTypeResponseDto;
import com.sale.baseinfo.database.entity.BusinessTypeEntity;
import com.sale.baseinfo.model.BusinessType;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface BusinessTypeMapper {

    BusinessType toModel(BusinessTypeEntity entity);

    List<BusinessType> toModelList(List<BusinessTypeEntity> businessTypeEntityList);

    List<BusinessTypeResponseDto> toDtoResponseList(List<BusinessType> businessTypeList);
    
    BusinessTypeResponseDto toDtoResponse(BusinessType businessTypeList);

}
