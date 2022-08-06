package com.tecnotree.demo.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(setterPrefix = "set", toBuilder = true, builderMethodName = "newInstance")
public class Comment {

    private long id;

    private long postId;

    private String name;

    private String email;

    private String body;

}
