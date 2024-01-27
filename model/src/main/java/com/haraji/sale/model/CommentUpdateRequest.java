package com.haraji.sale.model;

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
public class CommentUpdateRequest {

    private Long id;

    @NotNull
    @Min(1)
    private Long postId;

    @NotBlank
    private String body;

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", postId=" + postId +
                ", body='" + body + '\'' +
                '}';
    }
}
