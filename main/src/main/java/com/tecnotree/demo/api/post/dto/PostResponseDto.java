package com.tecnotree.demo.api.post.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(setterPrefix = "set", toBuilder = true, builderMethodName = "newInstance")
public class PostResponseDto implements Serializable {

    private long id;

    private long userId;

    private String title;

    private String body;
}
