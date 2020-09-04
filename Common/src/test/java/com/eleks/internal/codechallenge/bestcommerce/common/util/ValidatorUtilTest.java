package com.eleks.internal.codechallenge.bestcommerce.common.util;

import org.junit.Test;

import javax.validation.ValidationException;

public class ValidatorUtilTest {
    @Test
    public void passNormalPasswordTest() {
        ValidatorUtil.validatePassword("SomeNormalPass1234");
    }

    @Test(expected = ValidationException.class)
    public void passTooShortPasswordTest() {
        ValidatorUtil.validatePassword("Ab12");
    }

    @Test(expected = ValidationException.class)
    public void passPasswordWithInvalidSymbols1Test() {
        ValidatorUtil.validatePassword("p@ssw0rД");
    }

    @Test(expected = ValidationException.class)
    public void passPasswordWithInvalidSymbols2Test() {
        ValidatorUtil.validatePassword("some_pass");
    }

    @Test(expected = ValidationException.class)
    public void passTooLongPasswordTest() {
        ValidatorUtil.validatePassword("This password should be valid in some nearest future, because passwords with length less than twenty symbols became easy to guess using bruteforce");
    }
}
