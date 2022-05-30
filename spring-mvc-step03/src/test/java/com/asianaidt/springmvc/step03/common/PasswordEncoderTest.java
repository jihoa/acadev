package com.asianaidt.springmvc.step03.common;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;


class PasswordEncoderTest {
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void init() {
        passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Test
    void encode() {
        String password = "admin";
        String encPassword = passwordEncoder.encode(password);

        System.out.println(encPassword);
        assertThat(passwordEncoder.matches(password, encPassword)).isTrue();
        assertThat(encPassword).contains("{bcrypt}");
    }

    @Test
    void 암호변환기ID가_없는경우() {
        Pbkdf2PasswordEncoder passwordEncoder2 = new Pbkdf2PasswordEncoder();
        passwordEncoder2.encode("password");
        System.out.println(passwordEncoder2.encode("password"));
        System.out.println(passwordEncoder.encode("password"));


        String password = "password";
        //String encPassword = "$2a$10$Ot44NE6k1kO5bfNHTP0m8ejdpGr8ooHGT90lOD2/LpGIzfiS3p6oq";
        String encPassword   = "$2a$10$y8gTDBjmZgR7Ng68Fet4s.yX5Q2nOkiKQj2w3UtHcEDFLwdhbCoEy";

        // bcrypt
        DelegatingPasswordEncoder delegatingPasswordEncoder = (DelegatingPasswordEncoder) PasswordEncoderFactories.createDelegatingPasswordEncoder();
        delegatingPasswordEncoder.setDefaultPasswordEncoderForMatches(new BCryptPasswordEncoder());

        assertThat(delegatingPasswordEncoder.matches(password, encPassword)).isTrue();
    }

}
