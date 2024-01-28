package com.haraji.common.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(setterPrefix = "set", toBuilder = true, builderMethodName = "newInstance")
public class PageRequestDto implements Serializable {

    @ApiModelProperty(value = "شماره صفحه", dataType = "int", example = "1")
    private Integer page;

    @ApiModelProperty(value = "شمارگان اقلام هر صفحه", dataType = "int", example = "10")
    private Integer size;

}
