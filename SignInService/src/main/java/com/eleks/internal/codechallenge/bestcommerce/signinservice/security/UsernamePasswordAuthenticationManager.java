package com.eleks.internal.codechallenge.bestcommerce.signinservice.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class UsernamePasswordAuthenticationManager implements AuthenticationManager {

    @Autowired
    AuthenticationProvider provider;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        return provider.authenticate(authentication);
    }
}
