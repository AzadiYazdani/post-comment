package com.haraji.app.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
//@PropertySource("classpath:application.yml")
@Getter
public class AppConfig {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.token-string.username}")
    private String userNameString;

    @Value("${jwt.token-string.password}")
    private String passwordString;

    @Value("${jwt.token-string.header}")
    private String headerName;

    @Value("${jwt.token-string.prefix}")
    private String tokenPrefix;

    @Value("${jwt.timeout}")
    private long jwtTimeout;

    @Value("${ldap.url}")
    private String ldapUrl;

//    @Value("${ldap.port}")
//    private String ldapPort;

    @Value("${ldap.directory.root}")
    private String ldapRoot;

    @Value("${ldap.user.base}")
    private String ldapUserSearchBase;

    @Value("${ldap.user.filter}")
    private String ldapUserSearchFilter;

    @Value("${ldap.group.base}")
    private String groupBase;

    @Value("${ldap.factory}")
    private String ldapFactory;

    @Value("${ldap.domain}")
    private String ldapDomain;

    @Value("${ldap.security.authentication}")
    private String ldapType;

    @Value("${security.type}")
    private String securityType;
}
