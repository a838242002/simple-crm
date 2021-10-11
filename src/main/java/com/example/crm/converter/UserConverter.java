package com.example.crm.converter;

import com.example.crm.dto.user.CreateUserRequest;
import com.example.crm.dto.user.UpdateUserRequest;
import com.example.crm.entity.AppUser;

public class UserConverter {
    public static AppUser toEntity(CreateUserRequest request) {
        return AppUser.builder()
                .username(request.getUsername())
                .password(request.getPassword())
                .build();
    }

    public static AppUser toEntity(UpdateUserRequest request) {
        return AppUser.builder()
                .id(request.getId())
                .username(request.getUsername())
                .password(request.getPassword())
                .roles(RoleConverter.toEntity(request.getRoles()))
                .build();
    }
}
