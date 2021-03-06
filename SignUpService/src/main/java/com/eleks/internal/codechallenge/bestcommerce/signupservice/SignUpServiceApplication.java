package com.eleks.internal.codechallenge.bestcommerce.signupservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("com.eleks.internal.codechallenge.bestcommerce")
@EnableJpaRepositories("com.eleks.internal.codechallenge.bestcommerce.common.repository")
@EntityScan("com.eleks.internal.codechallenge.bestcommerce.common.entity")
public class SignUpServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SignUpServiceApplication.class, args);
    }

}
