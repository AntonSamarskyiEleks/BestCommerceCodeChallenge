package com.eleks.internal.codechallenge.bestcommerce.signinservice.security;

import com.eleks.internal.codechallenge.bestcommerce.signinservice.service.SignInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class UsernamePasswordAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private SignInService signInService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (authentication == null) {
            return null;
        }

        String email = authentication.getName();
        String password = authentication.getCredentials().toString();

        boolean authenticated = signInService.signIn(email, password);

        if (!authenticated) {
            throw new BadCredentialsException("Password is invalid");
        }

        return new UsernamePasswordAuthenticationToken(email, password,
                signInService.getUserDetails(email).getAuthorities());
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass == UsernamePasswordAuthenticationToken.class;
    }
}
