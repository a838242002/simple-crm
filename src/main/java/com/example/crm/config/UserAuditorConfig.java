package com.example.crm.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

@Configuration
public class UserAuditorConfig implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        SecurityContext ctx = SecurityContextHolder.getContext();
        Optional<String> auditor = Optional.empty();

        if (ctx != null && ctx.getAuthentication() != null && ctx.getAuthentication().getPrincipal() != null) {
            auditor = Optional.of((String) ctx.getAuthentication().getPrincipal());
        }

        return auditor;
    }
}
