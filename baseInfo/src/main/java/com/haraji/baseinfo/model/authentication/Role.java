package com.haraji.baseinfo.model.authentication;

import com.haraji.baseinfo.database.entity.authentication.UserEntity;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(setterPrefix = "set", toBuilder = true, builderMethodName = "newInstance")
public class Role {

    private Long id;

    private String title;
    private List<User> user;

    @Override
    public String toString() {
       StringBuilder builder = new StringBuilder();
        builder.append("title:"+ title);
        return builder.toString();
    }
}
