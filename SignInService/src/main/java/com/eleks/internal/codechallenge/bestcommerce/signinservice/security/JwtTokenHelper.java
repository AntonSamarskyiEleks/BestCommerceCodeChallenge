package com.eleks.internal.codechallenge.bestcommerce.signinservice.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtTokenHelper {
    public static final long ONE_HOUR = 60 * 60 * 1000;
    public static final long NORMAL_JWT_TOKEN_VALIDITY = 12 * ONE_HOUR;
    public static final long LONG_JWT_TOKEN_VALIDITY = 14 * NORMAL_JWT_TOKEN_VALIDITY;

    @Value("${jwt.secret}")
    private String secret;

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        return claimsResolver.apply(getAllClaimsFromToken(token));
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        return getExpirationDateFromToken(token).before(new Date());
    }

    public String generateToken(UserDetails userDetails, boolean longTerm) {
        return generateToken(new HashMap<String, Object>(), userDetails.getUsername(), longTerm);
    }

    private String generateToken(Map<String, Object> claims, String subject, boolean longTerm) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() +
                        (longTerm ? LONG_JWT_TOKEN_VALIDITY : NORMAL_JWT_TOKEN_VALIDITY)))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        return getUsernameFromToken(token)
                .equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

}