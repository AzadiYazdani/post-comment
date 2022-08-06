package com.tecnotree.demo.api.post.dto;

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
public class PostRequestDto implements Serializable {


    @ApiModelProperty(value = "User Id", dataType = "long", required = true, example = "1")
    @NotNull
    @Min(1)
    private Long userId;

    @ApiModelProperty(value = "body for post", dataType = "String", required = true, example = "\"quasi id et eos tenetur aut quo autem\"")
    @NotBlank
    private String title;

    @ApiModelProperty(value = "title of post", dataType = "String", required = true, example = "\"eum sed dolores ipsam sint possimus debitis occaecati\\ndebitis qui qui et\\nut placeat enim earum aut odit facilis\\nconsequatur suscipit necessitatibus rerum sed inventore temporibus consequatur\"")
    @NotBlank
    private String body;

    @Override
    public String toString() {
        return "PostRequestDto{" +
                "userId=" + userId +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
