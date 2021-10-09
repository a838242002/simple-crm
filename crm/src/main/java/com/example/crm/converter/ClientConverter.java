package com.example.crm.converter;

import com.example.crm.dto.client.CreateClientRequest;
import com.example.crm.dto.client.UpdateClientRequest;
import com.example.crm.entity.Client;
import com.example.crm.entity.Company;

import java.util.List;
import java.util.stream.Collectors;

public class ClientConverter {

    public static Client toEntity(CreateClientRequest request) {
        Company company = Company.builder()
                .id(request.getCompanyId())
                .build();

        return Client.builder()
                .name(request.getName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .company(company)
                .build();
    }

    public static List<Client> toEntity(List<CreateClientRequest> requests) {

        return requests.stream()
                .map(ClientConverter::toEntity)
                .collect(Collectors.toList());
    }

    public static Client toEntity(UpdateClientRequest request) {

        return Client.builder()
                .id(request.getId())
                .name(request.getName())
                .phone(request.getPhone())
                .build();
    }
}
