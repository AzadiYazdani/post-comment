package com.haraji.baseinfo.service.authenticarion;

import com.haraji.baseinfo.model.authentication.User;
import com.haraji.baseinfo.model.authentication.UserRequest;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public interface UserService {

    List<User> getAll();

    User getByName(String username);

    User getById(int id);

    User createUser(UserRequest userRequest);
}
