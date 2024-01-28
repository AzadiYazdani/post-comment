package com.haraji.app.service.token;

import com.haraji.app.config.AppConfig;
import com.haraji.app.service.ldap.LdapService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

import static java.util.Collections.emptyList;

@Service
@Slf4j
@Validated
public class TokenServiceImpl implements TokenService {

    private final AppConfig appConfig;

    private final LdapService ldapService;

    public TokenServiceImpl(AppConfig appConfig, LdapService ldapService) {
        this.appConfig = appConfig;
        this.ldapService = ldapService;
    }

    @Override
    public String newToken(@NotNull String username, @NotNull String password) {
        try {
            Key hmacKey = new SecretKeySpec(Base64.getDecoder().decode(appConfig.getSecretKey()),
                    SignatureAlgorithm.HS256.getJcaName());

            Instant now = Instant.now();
            String jwtToken = null;

            if (ldapService.validate(username, password)) {
                jwtToken = Jwts.builder()
                        .claim(appConfig.getUserNameString(), username)
                        .claim(appConfig.getPasswordString(), password)
                        .setSubject(username)
                        .setId(UUID.randomUUID().toString())
                        .setIssuedAt(Date.from(now))
                        .setExpiration(Date.from(now.plus(appConfig.getJwtTimeout(), ChronoUnit.MILLIS)))
                        .signWith(SignatureAlgorithm.HS256, hmacKey)
                        .compact();
            }
            return jwtToken;
        } catch (IncorrectResultSizeDataAccessException ex) {
            log.error("Unexpected result size returned from LDAP for search for user {}", username);

            if (ex.getActualSize() == 0) {
                throw new UsernameNotFoundException("User " + username + " not found in LDAP.");
            } else {
                throw ex;
            }
        }
    }

    @Override
    public Authentication verify(HttpServletRequest request) {
        String token = request.getHeader(appConfig.getHeaderName());
        if (token != null) {
            Claims claims;
            try {
                claims = Jwts.parser()
                        .setSigningKey(appConfig.getSecretKey())
                        .parseClaimsJws(token.replace(appConfig.getTokenPrefix(), ""))
                        .getBody();

            } catch (ExpiredJwtException e) {
                claims = e.getClaims();

                String username = claims.get(appConfig.getUserNameString()).toString();
                String password = claims.get(appConfig.getPasswordString()).toString();
                token = newToken(username, password);
            }
            String username = claims.get(appConfig.getUserNameString()).toString();
            String password = claims.get(appConfig.getPasswordString()).toString();

            if (ldapService.validate(username, password)) {
                return new UsernamePasswordAuthenticationToken(username, password, emptyList());
            }
        }
        return null;
    }

}
