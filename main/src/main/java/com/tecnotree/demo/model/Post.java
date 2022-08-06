package com.tecnotree.demo.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(setterPrefix = "set", toBuilder = true, builderMethodName = "newInstance")
public class Post {

    private long id;

    private long userId;

    private String title;

    private String body;

}
