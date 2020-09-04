package com.eleks.internal.codechallenge.bestcommerce.signupservice.controller;

import com.eleks.internal.codechallenge.bestcommerce.common.entity.Merchant;
import com.eleks.internal.codechallenge.bestcommerce.signupservice.service.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class SignUpController {

    @Autowired
    private SignUpService signUpService;

    @PostMapping(value = "/singup")
    public ResponseEntity<String> singUp(@RequestBody @Valid Merchant merchant) {
        return ResponseEntity.ok("Merchant profile created");
    }
}
