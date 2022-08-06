package com.tecnotree.demo.api.comment.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(setterPrefix = "with", toBuilder = true, builderMethodName = "newInstance")
public class CommentRequestDto {

    private long postId;

    private String name;

    private String email;

    private String body;

}
