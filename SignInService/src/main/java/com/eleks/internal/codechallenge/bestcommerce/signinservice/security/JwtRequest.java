package com.eleks.internal.codechallenge.bestcommerce.signinservice.security;

public class JwtRequest {

    private String email;
    private String password;
    private boolean longTerm;

    public JwtRequest() {
    }

    public JwtRequest(String email, String password, boolean longTerm) {
        this.setEmail(email);
        this.setPassword(password);
        this.setLongTerm(longTerm);
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLongTerm() {
        return longTerm;
    }

    public void setLongTerm(boolean longTerm) {
        this.longTerm = longTerm;
    }
}
