package com.eleks.internal.codechallenge.bestcommerce.signinservice.controller;

import com.eleks.internal.codechallenge.bestcommerce.signinservice.service.SignInService;
import com.eleks.internal.codechallenge.bestcommerce.signinservice.security.JwtRequest;
import com.eleks.internal.codechallenge.bestcommerce.signinservice.security.JwtResponse;
import com.eleks.internal.codechallenge.bestcommerce.signinservice.security.JwtTokenHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

// TODO Anton S.: token check should be available for other microservices,
// move impacted part to Common module (currently there are no such microservices)

@RestController
public class JwtAuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenHelper jwtTokenUtil;

    @Autowired
    private SignInService signInService;

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());

        final UserDetails userDetails = signInService.getUserDetails(authenticationRequest.getEmail());
        final String token = jwtTokenUtil.generateToken(userDetails, authenticationRequest.isLongTerm());

        return ResponseEntity.ok(new JwtResponse(token));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (AuthenticationException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
