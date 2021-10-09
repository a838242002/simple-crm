package com.example.crm.dto.user;

import com.example.crm.dto.role.UpdateRoleRequest;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class UpdateUserRequest {

    @NotNull
    Long id;

    @NotNull
    String username;

    @NotNull
    String password;

    List<UpdateRoleRequest> roles;
}
