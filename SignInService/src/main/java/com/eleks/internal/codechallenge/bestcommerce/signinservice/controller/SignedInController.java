package com.eleks.internal.codechallenge.bestcommerce.signinservice.controller;

import com.eleks.internal.codechallenge.bestcommerce.common.entity.Merchant;
import com.eleks.internal.codechallenge.bestcommerce.signinservice.service.SignInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class SignedInController {

    @Autowired
    private SignInService signInService;

    @GetMapping("/info")
    public ResponseEntity<Merchant> showInfo(Principal principal) {
        return ResponseEntity.ok(signInService.getMerchantInfo(principal.getName()));
    }

}
