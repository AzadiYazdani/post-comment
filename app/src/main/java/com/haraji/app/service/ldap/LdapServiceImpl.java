package com.haraji.app.service.ldap;

import com.haraji.app.config.AppConfig;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import javax.naming.Context;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Service
public class LdapServiceImpl implements LdapService {
    private final AppConfig appConfig;

    public LdapServiceImpl(AppConfig appConfig) {
        this.appConfig = appConfig;
    }

    @Override
    public boolean validate(String username, String password) {
        try {
            Properties props = new Properties();
            props.put(Context.INITIAL_CONTEXT_FACTORY, appConfig.getLdapFactory());
            props.put(Context.PROVIDER_URL, appConfig.getLdapUrl());
            props.put(Context.SECURITY_AUTHENTICATION, appConfig.getSecurityType());
            props.put(Context.SECURITY_PRINCIPAL, username.concat("@").concat(appConfig.getLdapDomain()));
            props.put(Context.SECURITY_CREDENTIALS, password);

            LdapContext context = new InitialLdapContext(props, null);
            List<GrantedAuthority> roles = new ArrayList<>();
            User user = new User(username, password, roles);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

}
