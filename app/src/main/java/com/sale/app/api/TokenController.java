package com.sale.app.api;

import com.sale.app.model.dto.AuthRequest;
import com.sale.app.service.token.TokenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authentication")
@Api(value = "token operations")
@Slf4j
public class TokenController {

    private final TokenService tokenService;

    public TokenController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @PostMapping(value = "/login")
    @ApiOperation(value = "دریافت توکن", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> login(@RequestBody @NonNull @ApiParam(value = "نام کاربری و گذواژه دامنه برای جستجو در LDAP", required = true) AuthRequest authRequest) {
        log.info("get token");
        String token = tokenService.newToken(authRequest.getUsername(), authRequest.getPassword());
        return new ResponseEntity<>(token, HttpStatus.OK);
    }
}
