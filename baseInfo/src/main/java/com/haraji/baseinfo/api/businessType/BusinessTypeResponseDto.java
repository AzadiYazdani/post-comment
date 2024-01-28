package com.haraji.baseinfo.api.businessType;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(setterPrefix = "set", toBuilder = true, builderMethodName = "newInstance")
public class BusinessTypeResponseDto {

    @ApiModelProperty(value = "شناسه کسب و کار", dataType = "long", required = true, example = "1")
    private long id;

    @ApiModelProperty(value = "نوع کسب و کار", dataType = "String", required = true, example = "\"پوشاک زنانه\"")
    private String title;

    @Override
    public String toString() {
        return "CityResponseDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
