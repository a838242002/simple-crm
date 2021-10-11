package com.example.crm.dto.company;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CreateCompanyRequest {

    @NotNull
    String name;

    @NotNull
    String address;
}
