package com.tecnotree.demo.api.todo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(setterPrefix = "set", toBuilder = true, builderMethodName = "newInstance")
@Valid
public class ToDoRequestDto implements Serializable {


    @ApiModelProperty(value = "User Id", dataType = "long", required = true, example = "1")
    @NotNull
    @Min(1)
    private Long userId;

    @ApiModelProperty(value = "title of todo", dataType = "String", required = true, example = "\"quasi id et eos tenetur aut quo autem\"")
    @NotBlank
    private String title;

    @ApiModelProperty(value = "is the todo completed", dataType = "boolean", required = true, example = "\true\"")
    @NotNull
    private Boolean completed;

    @Override
    public String toString() {
        return "ToDoRequestDto{" +
                "userId=" + userId +
                ", title='" + title + '\'' +
                ", completed=" + completed +
                '}';
    }
}
