package com.tecnotree.demo.api.comment.dto;

import com.tecnotree.demo.model.Post;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(setterPrefix = "with", toBuilder = true, builderMethodName = "newInstance")
public class CommentResponseDto {

    private long id;

    private long postId;

    private String name;

    private String email;

    private String body;

}
