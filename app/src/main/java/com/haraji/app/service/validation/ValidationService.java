package com.haraji.app.service.validation;


import org.springframework.security.core.userdetails.User;

public interface ValidationService {
    User validate(String username, String password);
}
