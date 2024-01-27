package com.haraji.sale.database.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(schema = "haraji",name = "TBL_USER")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(setterPrefix = "set", toBuilder = true, builderMethodName = "newInstance")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @OneToMany(targetEntity = PostEntity.class, mappedBy = "user", fetch = FetchType.LAZY)
    private List<PostEntity> posts;
}
