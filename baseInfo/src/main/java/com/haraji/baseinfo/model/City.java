package com.haraji.baseinfo.model;

import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(setterPrefix = "set", toBuilder = true, builderMethodName = "newInstance")
@Valid
public class City {

    private int id;

    @NotNull
    @Min(1)
    private int stateId;

    @NotBlank
    private String title;

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", stateId=" + stateId +
                ", title='" + title + '\'' +
                '}';
    }
}
