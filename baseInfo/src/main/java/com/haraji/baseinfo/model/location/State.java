package com.haraji.baseinfo.model.location;

import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(setterPrefix = "set", toBuilder = true, builderMethodName = "newInstance")
@Valid
public class State {

    private Long id;

    @NotBlank
    private String title;


    @Override
    public String toString() {
        return "state{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
