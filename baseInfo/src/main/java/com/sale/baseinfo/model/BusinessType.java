package com.sale.baseinfo.model;

import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(setterPrefix = "set", toBuilder = true, builderMethodName = "newInstance")
@Valid
public class BusinessType {
    private Long id;

    @NotBlank
    private String title;

    @Override
    public String toString() {
        return "activityType{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
