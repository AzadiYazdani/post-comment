package com.tecnotree.demo.api.post.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(setterPrefix = "set", toBuilder = true, builderMethodName = "newInstance")
public class PostResponseDto implements Serializable {

    @ApiModelProperty(value = "Identifier of post", dataType = "long", example = "13")
    private Long id;

    @ApiModelProperty(value = "User Id", dataType = "long", example = "1")
    private Long userId;

    @ApiModelProperty(value = "body for post", dataType = "String", example = "\"quasi id et eos tenetur aut quo autem\"")
    private String title;

    @ApiModelProperty(value = "title of post", dataType = "String", example = "\"eum sed dolores ipsam sint possimus debitis occaecati\\ndebitis qui qui et\\nut placeat enim earum aut odit facilis\\nconsequatur suscipit necessitatibus rerum sed inventore temporibus consequatur\"")
    private String body;

    @Override
    public String toString() {
        return "PostResponseDto{" +
                "id=" + id +
                ", userId=" + userId +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
