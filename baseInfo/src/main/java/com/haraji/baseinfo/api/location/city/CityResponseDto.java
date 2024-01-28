package com.haraji.baseinfo.api.location.city;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(setterPrefix = "set", toBuilder = true, builderMethodName = "newInstance")
public class CityResponseDto {

    @ApiModelProperty(value = "شناسه شهر", dataType = "long", required = true, example = "1")
    private int id;

    @ApiModelProperty(value = "شناسه استان", dataType = "long", required = true, example = "1")
    private int stateId;

    @ApiModelProperty(value = "نام شهر", dataType = "String", required = true, example = "\"ابهر\"")
    private String title;

    @Override
    public String toString() {
        return "CityResponseDto{" +
                "id=" + id +
                ", stateId=" + stateId +
                ", title='" + title + '\'' +
                '}';
    }
}
