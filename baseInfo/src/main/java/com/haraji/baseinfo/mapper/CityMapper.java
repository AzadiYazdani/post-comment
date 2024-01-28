package com.haraji.baseinfo.mapper;


import com.haraji.baseinfo.api.location.city.CityResponseDto;
import com.haraji.baseinfo.model.City;
import com.haraji.baseinfo.database.entity.CityEntity;
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
