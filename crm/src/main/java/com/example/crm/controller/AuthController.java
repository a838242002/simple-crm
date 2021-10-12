package com.example.crm.controller;

import com.example.crm.dto.auth.AuthRequest;
import com.example.crm.dto.basic.BasicResponse;
import com.example.crm.service.security.JwtService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
@Api(tags = "JWT Authentication")
public class AuthController {

    @Autowired
    JwtService jwtService;

    @PostMapping(value = "/login", consumes="application/json")
    public ResponseEntity<BasicResponse> issueToken(@RequestBody AuthRequest request) {
        Map<String, String> responseBody;

        try {
            String token = jwtService.generateToken(request.getUsername(), request.getPassword());
            responseBody = Collections.singletonMap("token", token);
        } catch (Exception e) {
            log.error(e.toString());
            return new ResponseEntity<>(BasicResponse.fail(e.getMessage()), HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(BasicResponse.success(responseBody));
    }
}
