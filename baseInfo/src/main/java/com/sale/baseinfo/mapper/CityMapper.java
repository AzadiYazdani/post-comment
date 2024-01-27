package com.sale.baseinfo.mapper;


import com.sale.baseinfo.api.location.city.CityResponseDto;
import com.sale.baseinfo.database.entity.CityEntity;
import com.sale.baseinfo.model.City;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;


@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface CityMapper {

    @Mapping(target = "stateId", source = "entity.state.id")
     City toModel(CityEntity entity);

      List<City> toModelList(List<CityEntity> cityEntityList);

      List<CityResponseDto> toDtoResponseList(List<City> postList);

}
