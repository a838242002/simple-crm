package com.example.crm.dto.company;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateCompanyRequest {

    Long id;

    String name;

    String address;
}
