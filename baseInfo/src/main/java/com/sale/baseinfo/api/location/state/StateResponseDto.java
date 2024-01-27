package com.sale.baseinfo.api.location.state;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(setterPrefix = "set", toBuilder = true, builderMethodName = "newInstance")
public class StateResponseDto implements Serializable {

    @ApiModelProperty(value = "شناسه استان", dataType = "int", example = "1")
    private int id;

    @ApiModelProperty(value = "نام استان", dataType = "String", example = "\"آذربایجان شرقی\"")
    private String title;

    @Override
    public String toString() {
        return "StateResponseDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
