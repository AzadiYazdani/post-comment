package com.tecnotree.demo.api.comment.dto;

import io.swagger.annotations.ApiModelProperty;
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
public class CommentRequestDto {

    @ApiModelProperty(value = "Post Id", dataType = "long", required = true, example = "1")
    @NotNull
    @Min(1)
    private Long postId;

    @ApiModelProperty(value = "name of comment", dataType = "String", required = true, example = "\"odio adipisci rerum aut animi\"")
    @NotBlank
    private String name;

    @ApiModelProperty(value = "email of commenter", dataType = "String", required = true, example = "\"Nikita@garfield.biz\"")
    @NotBlank
    private String email;

    @ApiModelProperty(value = "body for comment", dataType = "String", required = true, example = "\"quia molestiae reprehenderit quasi aspernatur\\naut expedita occaecati aliquam eveniet laudantium\\nomnis quibusdam delectus saepe quia accusamus maiores nam est\\ncum et ducimus et vero voluptates excepturi deleniti ratione\"")
    @NotBlank
    private String body;

    @Override
    public String toString() {
        return "CommentRequestDto{" +
                "postId=" + postId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
