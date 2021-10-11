package com.example.crm.converter;

import com.example.crm.dto.company.CreateCompanyRequest;
import com.example.crm.dto.company.UpdateCompanyRequest;
import com.example.crm.entity.Company;

public class CompanyConverter {

    public static Company toEntity(CreateCompanyRequest request) {
        return Company.builder()
                .name(request.getName())
                .address(request.getAddress())
                .build();
    }

    public static Company toEntity(UpdateCompanyRequest request) {
        return Company.builder()
                .id(request.getId())
                .name(request.getName())
                .address(request.getAddress())
                .build();
    }
}
