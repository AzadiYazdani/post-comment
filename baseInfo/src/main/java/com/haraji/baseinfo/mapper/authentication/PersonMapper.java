package com.haraji.baseinfo.mapper.authentication;


import com.haraji.baseinfo.api.authentication.UserRequestDto;
import com.haraji.baseinfo.api.authentication.UserResponseDto;
import com.haraji.baseinfo.database.entity.authentication.UserEntity;
import com.haraji.baseinfo.model.authentication.Person;
import com.haraji.baseinfo.database.entity.authentication.PersonEntity;
import com.haraji.baseinfo.model.authentication.UserRequest;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface PersonMapper {

    UserRequest toRequestModel(UserRequestDto userRequestDto);

    Person toModel(PersonEntity entity);

    List<Person> toModelList(List<PersonEntity> entities);

    PersonEntity toEntity(UserRequest userRequest);

    List<UserResponseDto> toDtoResponseList(List<Person> businessTypeList);

    UserResponseDto toDtoResponse(Person userList);

}
