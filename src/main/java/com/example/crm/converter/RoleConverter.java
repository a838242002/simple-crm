package com.example.crm.converter;

import com.example.crm.dto.role.CreateRoleRequest;
import com.example.crm.dto.role.UpdateRoleRequest;
import com.example.crm.entity.Role;

import java.util.List;
import java.util.stream.Collectors;

public class RoleConverter {
    public static Role toEntity(UpdateRoleRequest request) {
        return Role.builder()
                .id(request.getId())
                .roleName(request.getRoleName())
                .build();
    }

    public static List<Role> toEntity(List<UpdateRoleRequest> requests) {
        return requests.stream()
                .map(RoleConverter::toEntity)
                .collect(Collectors.toList());
    }

    public static Role toEntity(CreateRoleRequest request) {
        return Role.builder()
                .roleName(request.getRoleName())
                .build();
    }
}
