package com.example.crm.controller;

import com.example.crm.dto.auth.AuthRequest;
import com.example.crm.service.security.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@Slf4j
public class AuthController {

    @Autowired
    JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> issueToken(@RequestBody AuthRequest request) {
        String token = jwtService.generateToken(request.getUsername(), request.getPassword());
        Map<String, String> response = Collections.singletonMap("token", token);

        return ResponseEntity.ok(response);
    }
}
