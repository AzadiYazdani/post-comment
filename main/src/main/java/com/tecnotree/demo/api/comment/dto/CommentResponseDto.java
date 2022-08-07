package com.tecnotree.demo.api.comment.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(setterPrefix = "set", toBuilder = true, builderMethodName = "newInstance")
public class CommentResponseDto {

    private long id;

    @ApiModelProperty(value = "Post Id", dataType = "long", required = true, example = "11")
    private Long postId;

    @ApiModelProperty(value = "name of commenter", dataType = "String", required = true, example = "\"quasi id et eos tenetur aut quo autem\"")
    private String name;

    @ApiModelProperty(value = "email of commenter", dataType = "String", required = true, example = "\"quasi id et eos tenetur aut quo autem\"")
    private String email;

    @ApiModelProperty(value = "body of comment", dataType = "String", required = true, example = "\"quasi id et eos tenetur aut quo autem\"")
    private String body;

    @Override
    public String toString() {
        return "CommentResponseDto{" +
                "id=" + id +
                ", postId=" + postId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
