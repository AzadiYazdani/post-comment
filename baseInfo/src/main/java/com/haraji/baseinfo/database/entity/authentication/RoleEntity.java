package com.haraji.baseinfo.database.entity.authentication;

import com.haraji.baseinfo.database.repository.authentication.UserRepository;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(schema = "sale_db",name = "role")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
@Builder(setterPrefix = "set", toBuilder = true, builderMethodName = "newInstance")
public class RoleEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;


    @ManyToMany
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<UserEntity> user;

    @Override
    public String toString() {
       StringBuilder builder = new StringBuilder();
        builder.append("title:"+ title);
        return builder.toString();
    }
}
