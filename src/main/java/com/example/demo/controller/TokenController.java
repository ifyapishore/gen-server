package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TokenController {

    private final TokenService tokenService;

    public TokenController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @GetMapping("/validate")
    public ResponseEntity<TokenStatus> validate(@RequestParam String token) {
        try {
            var status = tokenService.decryptAndValidate(token);
            return ResponseEntity.ok(status);
        } catch (Exception e) {
            return ResponseEntity.status(403).build();
        }
    }

    @PostMapping("/issue")
    public ResponseEntity<String> issue(@RequestBody String originalToken) {
        String token = tokenService.encryptToken(originalToken);
        return ResponseEntity.ok(token);
    }
}
