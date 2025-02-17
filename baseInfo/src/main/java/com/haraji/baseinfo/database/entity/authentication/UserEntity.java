package com.haraji.baseinfo.database.entity.authentication;

import com.haraji.baseinfo.constant.RoleEnum;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(schema = "sale_db",name = "user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
@Builder(setterPrefix = "set", toBuilder = true, builderMethodName = "newInstance")
public class UserEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String email;

    @Column (name="create_time")
    private LocalDateTime createTime;

    @Column (name="expire_time")
    private LocalDateTime expireTime;

    @Enumerated(EnumType.ORDINAL)
    @Column(name="role")
    private RoleEnum role;

    @OneToOne
    private PersonEntity person;

}
