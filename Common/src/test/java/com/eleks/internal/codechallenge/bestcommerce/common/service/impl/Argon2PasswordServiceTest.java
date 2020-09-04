package com.eleks.internal.codechallenge.bestcommerce.common.service.impl;

import org.junit.Test;

import static org.junit.Assert.*;

public class Argon2PasswordServiceTest {

    private final Argon2PasswordService argon2PasswordService = new Argon2PasswordService();

    @Test
    public void checkHashGeneration() {
        final String password = "somePassword";

        String hash = argon2PasswordService.hash(password);

        assertNotNull(hash);
        assertFalse(hash.contains(password));
    }

    @Test
    public void checkSameHash() {
        final String password = "somePassword";

        String hash = argon2PasswordService.hash(password);
        boolean result = argon2PasswordService.verify(hash, password);

        assertTrue(result);
    }

    @Test
    public void checkOtherHash() {
        final String password = "somePassword";
        final String otherPassword = "some0therPassword";

        String hash = argon2PasswordService.hash(password);
        boolean result = argon2PasswordService.verify(hash, otherPassword);

        assertFalse(result);
    }
}
