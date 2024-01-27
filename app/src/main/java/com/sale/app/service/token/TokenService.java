package com.sale.app.service.token;


import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;

public interface TokenService {

    String newToken(@NotNull String username, @NotNull String password);

    Authentication verify(HttpServletRequest request);
}
