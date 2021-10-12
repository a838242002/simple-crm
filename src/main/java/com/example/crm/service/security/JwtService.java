package com.example.crm.service.security;

import com.example.crm.dto.auth.AuthRequest;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Calendar;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class JwtService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Value("${jwt.key}")
    String key;

    @Value("${jwt.alive.minute}")
    int aliveMinute;

    public String generateToken(String username, String password) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(username, password);
        authentication = authenticationManager.authenticate(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, aliveMinute);

        Claims claims = Jwts.claims();
        claims.put("username", userDetails.getUsername());
        claims.setExpiration(calendar.getTime());
        claims.setIssuer("JWT Provider");

        Key secretKey = Keys.hmacShaKeyFor(key.getBytes(StandardCharsets.UTF_8));

        return Jwts.builder()
                .setClaims(claims)
                .signWith(secretKey)
                .compact();

    }

    public Map<String, Object> parseToken(String token) {
        Key secretKey = Keys.hmacShaKeyFor(key.getBytes(StandardCharsets.UTF_8));

        JwtParser parser = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build();

        Claims claims = parser
                .parseClaimsJws(token)
                .getBody();

        return claims.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
