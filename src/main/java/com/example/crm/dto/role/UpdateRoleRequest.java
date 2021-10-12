package com.example.crm.dto.role;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UpdateRoleRequest {

    @NotNull
    Long id;

    @NotNull
    String roleName;
}
