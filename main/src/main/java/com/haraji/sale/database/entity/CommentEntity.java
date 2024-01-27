package com.haraji.sale.database.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(schema = "haraji", name = "TBL_COMMENT")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(setterPrefix = "set", toBuilder = true, builderMethodName = "newInstance")
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @ManyToOne
    @JoinColumn(name = "post_id")
    private PostEntity post;

    private String name;

    private String email;

    private String body;

    private boolean deleted;

    private LocalDateTime registerTime;

    private LocalDateTime updateTime;
}
