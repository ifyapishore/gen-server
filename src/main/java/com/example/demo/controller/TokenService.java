package com.example.demo.controller;

import org.springframework.stereotype.Service;

@Service
public class TokenService {
    public TokenStatus decryptAndValidate(String token) {
        return TokenStatus.ok;
    }

    public String encryptToken(String originalToken) {
        return null;
    }
}
