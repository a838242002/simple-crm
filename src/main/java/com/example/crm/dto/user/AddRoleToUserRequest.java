package com.example.crm.dto.user;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class AddRoleToUserRequest {

    @NotNull
    Long userId;

    @NotNull
    Long roleId;
}
