package com.example.crm.dto.user;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CreateUserRequest {

    @NotNull
    String username;

    @NotNull
    String password;
}
