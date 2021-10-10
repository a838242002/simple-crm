package com.example.crm.dto.client;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UpdateClientRequest {

    @NotNull
    Long id;

    @NotNull
    String name;

    @NotNull
    String email;

    @NotNull
    String phone;
}
