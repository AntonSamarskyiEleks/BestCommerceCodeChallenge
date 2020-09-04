package com.eleks.internal.codechallenge.bestcommerce.common.service.impl;

import com.eleks.internal.codechallenge.bestcommerce.common.service.PasswordService;
import org.springframework.stereotype.Service;

@Service
public class Argon2PasswordService implements PasswordService {
    @Override
    public String hash(String password) {
        return password; // TODO implement
    }

    @Override
    public boolean verify(String hash, String password) {
        return false; // TODO implement
    }
}
