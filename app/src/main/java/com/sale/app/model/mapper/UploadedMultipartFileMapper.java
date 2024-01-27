package com.sale.app.model.mapper;

import com.sale.app.model.ThunderUploadedMultipartFile;
import com.sale.app.model.dto.UploadedMultipartFileDto;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public abstract class UploadedMultipartFileMapper {
    public abstract ThunderUploadedMultipartFile toModel(UploadedMultipartFileDto dto);
}
