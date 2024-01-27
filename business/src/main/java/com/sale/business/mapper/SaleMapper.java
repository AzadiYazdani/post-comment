package com.sale.business.mapper;


import com.haraji.sale.mapper.StructMapper;
import com.sale.business.api.SaleResponseDto;
import com.sale.business.database.entity.SaleViewEntity;
import com.sale.business.model.SaleView;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public abstract class SaleMapper extends StructMapper<SaleView, SaleViewEntity, SaleResponseDto> {


//    public abstract SaleView toModel(SaleViewEntity entity);
//
//    public abstract List<SaleView> toModelList(List<SaleViewEntity> stateEntityList);
//
//    public abstract SaleViewResponseDto toDtoResponse(SaleView entity);
//
//    public abstract List<SaleViewResponseDto> toDtoResponseList(List<SaleView> stateList);

}
