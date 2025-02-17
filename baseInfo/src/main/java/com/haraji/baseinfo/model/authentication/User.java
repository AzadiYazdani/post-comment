package com.haraji.baseinfo.model.authentication;

import com.haraji.baseinfo.constant.RoleEnum;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(setterPrefix = "set", toBuilder = true, builderMethodName = "newInstance")
public class User {

    private Long id;

    private String username;
    private String password;
    private String email;
    private LocalDateTime createTime;
    private LocalDateTime expireTime;
    private RoleEnum role;
    private Person person;
}
