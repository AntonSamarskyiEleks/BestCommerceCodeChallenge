package com.eleks.internal.codechallenge.bestcommerce.common.service;

import com.eleks.internal.codechallenge.bestcommerce.common.util.ValidatorUtil;
import org.springframework.stereotype.Service;

@Service
public interface PasswordService {
    default void validate(String password) {
        ValidatorUtil.validatePassword(password);
    }

    String hash(String password);

    boolean verify(String hash, String password);
}
