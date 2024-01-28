package com.haraji.baseinfo.mapper;


import com.haraji.baseinfo.api.location.state.StateResponseDto;
import com.haraji.baseinfo.model.State;
import com.haraji.baseinfo.database.entity.StateEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface StateMapper {


    State toModel(StateEntity entity);

    List<State> toModelList(List<StateEntity> stateEntityList);

    StateResponseDto toDtoResponse(State entity);

    List<StateResponseDto> toDtoResponseList(List<State> stateList);

}
