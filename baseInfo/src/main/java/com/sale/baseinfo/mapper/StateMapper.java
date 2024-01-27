package com.sale.baseinfo.mapper;


import com.sale.baseinfo.api.location.state.StateResponseDto;
import com.sale.baseinfo.database.entity.StateEntity;
import com.sale.baseinfo.model.State;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface StateMapper {


    public abstract State toModel(StateEntity entity);

    public abstract List<State> toModelList(List<StateEntity> stateEntityList);

    public abstract StateResponseDto toDtoResponse(State entity);

    public abstract List<StateResponseDto> toDtoResponseList(List<State> stateList);

}
