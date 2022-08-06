package com.tecnotree.demo.api.post.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(setterPrefix = "set", toBuilder = true, builderMethodName = "newInstance")
public class PageRequestDto implements Serializable {

    @ApiModelProperty(value = "Page number", dataType = "int", example = "0")
    private Integer page;

    @ApiModelProperty(value = "Page size", dataType = "int", example = "10")
    private Integer size;

}
