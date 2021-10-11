package com.example.crm.controller;

import com.example.crm.converter.CompanyConverter;
import com.example.crm.dto.company.CreateCompanyRequest;
import com.example.crm.dto.company.DeleteCompanyRequest;
import com.example.crm.dto.company.UpdateCompanyRequest;
import com.example.crm.entity.Company;
import com.example.crm.service.CompanyService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/companies")
@Slf4j
@Api(tags = "Company Management")
public class CompanyController {

    @Autowired
    CompanyService companyService;

    @PostMapping(consumes="application/json")
    public ResponseEntity<Company> createCompany(@Valid @RequestBody CreateCompanyRequest request) {
        Company company;

        try {
            company = companyService.createCompany(CompanyConverter.toEntity(request));
        } catch (Exception e) {
            log.error(e.toString());
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(company);
    }

    @GetMapping
    public ResponseEntity<List<Company>> getCompanies() {
        List<Company> companies;

        try {
            companies = companyService.getCompanies();
        } catch (Exception e) {
            log.error(e.toString());
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(companies);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompany(@PathVariable Long id) {
        Company company;

        try {
            company = companyService.getCompany(id);
        } catch (Exception e) {
            log.error(e.toString());
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(company);
    }

    @PutMapping(consumes="application/json")
    public ResponseEntity<Company> updateCompany(@Valid @RequestBody UpdateCompanyRequest request) {
        Company company;

        try {
            company = companyService.updateCompany(CompanyConverter.toEntity(request));
        } catch (Exception e) {
            log.error(e.toString());
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(company);
    }

    @DeleteMapping(consumes="application/json")
    public ResponseEntity<String> deleteCompany(@Valid @RequestBody DeleteCompanyRequest request) {
        String result;

        try {
            result = companyService.deleteCompany(request.getId());
        } catch (Exception e) {
            log.error(e.toString());
            return new ResponseEntity<>("Delete failed", HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(result);
    }
}
