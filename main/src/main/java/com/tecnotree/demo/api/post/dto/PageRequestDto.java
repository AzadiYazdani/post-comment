package com.tecnotree.demo.api.post.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(setterPrefix = "set", toBuilder = true, builderMethodName = "newInstance")
public class PageRequestDto implements Serializable {

    private int page;

    private int size;

}
