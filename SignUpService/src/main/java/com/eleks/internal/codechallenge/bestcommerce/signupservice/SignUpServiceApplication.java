package com.eleks.internal.codechallenge.bestcommerce.signupservice;

import main.java.com.eleks.internal.codechallenge.bestcommerce.common.Constants;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SignUpServiceApplication {

	public static void main(String[] args) {
		System.out.println(Constants.SOME_CONSTANT);
		SpringApplication.run(SignUpServiceApplication.class, args);
	}

}
