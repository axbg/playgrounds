package com.example.oauthjwttest.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.oidc.StandardClaimNames;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.Collection;

public class CustomAuthToken extends JwtAuthenticationToken {
    public CustomAuthToken(Jwt jwt, Collection<? extends GrantedAuthority> authorities) {
        super(jwt, authorities);
    }

    @Override
    public String getPrincipal() {
        return getToken().getClaimAsString(StandardClaimNames.PREFERRED_USERNAME);
    }
}
