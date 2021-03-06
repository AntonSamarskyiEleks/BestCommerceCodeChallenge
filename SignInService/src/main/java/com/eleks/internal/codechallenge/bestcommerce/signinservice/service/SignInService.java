package com.eleks.internal.codechallenge.bestcommerce.signinservice.service;

import com.eleks.internal.codechallenge.bestcommerce.common.entity.Merchant;
import com.eleks.internal.codechallenge.bestcommerce.common.repository.MerchantRepository;
import com.eleks.internal.codechallenge.bestcommerce.common.service.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SignInService implements UserDetailsService, PasswordEncoder {

    @Autowired
    MerchantRepository merchantRepository;

    @Autowired
    PasswordService passwordService;

    public boolean signIn(String email, String password) {
        Merchant user = getMerchant(email);
        return passwordService.verify(user.getPassword(), password);
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        Merchant merchant = getMerchant(email);
        return User.builder()
                .username(merchant.getEmail())
                .password(merchant.getPassword())
                .roles("user") // current implementation has only one role
                .accountLocked(false) // current implementation has no locking
                .build();
    }

    public Merchant getMerchantInfo(String email) {
        Merchant merchant = getMerchant(email);
        hidePassword(merchant);
        return merchant;
    }

    private Merchant getMerchant(String email) {
        Merchant user = merchantRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found: " + email);
        }
        return user;
    }

    private void hidePassword(Merchant merchant) {
        merchant.setPassword("******");
    }

    @Override
    public String encode(CharSequence password) {
        return passwordService.hash(String.valueOf(password));
    }

    @Override
    public boolean matches(CharSequence password, String hash) {
        return passwordService.verify(hash, String.valueOf(password));
    }
}
