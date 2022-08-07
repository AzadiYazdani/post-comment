package com.tecnotree.demo.api.todo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(setterPrefix = "set", toBuilder = true, builderMethodName = "newInstance")
public class ToDoResponseDto implements Serializable {

    @ApiModelProperty(value = "Identifier of post", dataType = "long", example = "1")
    private Long id;

    @ApiModelProperty(value = "User Id", dataType = "long", example = "6")
    private Long userId;

    @ApiModelProperty(value = "title of post", dataType = "String", example = "\"qui ullam ratione quibusdam voluptatem quia omnis\"")
    private String title;

    @ApiModelProperty(value = "is the todo completed", dataType = "boolean", required = true, example = "\true\"")
    private Boolean completed;

    @Override
    public String toString() {
        return "ToDoResponseDto{" +
                "id=" + id +
                ", userId=" + userId +
                ", title='" + title + '\'' +
                ", completed=" + completed +
                '}';
    }
}
