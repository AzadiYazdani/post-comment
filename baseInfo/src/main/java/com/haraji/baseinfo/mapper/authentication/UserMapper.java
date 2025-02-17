package com.haraji.baseinfo.mapper.authentication;


import com.haraji.baseinfo.api.authentication.UserRequestDto;
import com.haraji.baseinfo.api.authentication.UserResponseDto;
import com.haraji.baseinfo.database.entity.authentication.UserEntity;
import com.haraji.baseinfo.model.authentication.User;
import com.haraji.baseinfo.model.authentication.UserRequest;
import com.haraji.common.util.DateUtility;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Component
@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public abstract class UserMapper {

    @Mapping(source = "birthDate", target = "birthDate", ignore = true)
    public abstract UserRequest toRequestModel(UserRequestDto userRequestDto);

    public abstract User toModel(UserEntity entity);

    public abstract List<User> toModelList(List<UserEntity> businessTypeEntityList);

    public abstract UserEntity toEntity(UserRequest userRequest);

    public abstract List<UserResponseDto> toDtoResponseList(List<User> businessTypeList);

    public abstract UserResponseDto toDtoResponse(User userList);

    @AfterMapping
    protected void afterMap(UserRequestDto source, @MappingTarget UserRequest target) {
        if (source == null || target == null) {
            return;
        }
        if (source.getBirthDate() != null) {
            try {
                LocalDate dateLocalDate = DateUtility.getLocalDateFromString(source.getBirthDate());
                target.setBirthDate(dateLocalDate);
            }catch (Exception e){
                target.setBirthDate(null);
            }

        }
    }

    @AfterMapping
    protected void afterMap(UserRequest source, @MappingTarget UserEntity target) {
        if (source == null || target == null) {
            return;
        }
        target.setCreateTime(LocalDateTime.now());
    }
}
