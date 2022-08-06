package com.tecnotree.demo.database.entity;

import lombok.*;

import javax.persistence.*;


@Entity
@Table(schema = "tecnotree", name = "TBL_COMMENT")
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


}
