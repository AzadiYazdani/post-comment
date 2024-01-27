package com.haraji.sale.database.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(schema = "haraji",name = "TBL_POST")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(setterPrefix = "set", toBuilder = true, builderMethodName = "newInstance")
public class PostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @OneToMany(targetEntity = CommentEntity.class, mappedBy = "post", fetch = FetchType.LAZY)
    private List<CommentEntity> comments;

    private String title;

    private String body;

    private boolean deleted;

    private LocalDateTime registerTime;

    private LocalDateTime updateTime;

}
