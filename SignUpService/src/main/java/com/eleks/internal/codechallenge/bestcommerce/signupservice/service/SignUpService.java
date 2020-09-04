package com.eleks.internal.codechallenge.bestcommerce.signupservice.service;

import com.eleks.internal.codechallenge.bestcommerce.common.entity.Merchant;
import com.eleks.internal.codechallenge.bestcommerce.common.repository.MerchantRepository;
import com.eleks.internal.codechallenge.bestcommerce.common.service.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignUpService {

    @Autowired
    MerchantRepository merchantRepository;

    @Autowired
    PasswordService passwordService;
    public void signUp(Merchant merchant){
        String rawPassword = merchant.getPassword();

        passwordService.validate(rawPassword);

        merchant.setPassword(passwordService.hash(rawPassword));
    }
}
