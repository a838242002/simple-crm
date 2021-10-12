package com.example.crm.controller;

import com.example.crm.converter.CompanyConverter;
import com.example.crm.dto.basic.BasicResponse;
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
    public ResponseEntity<BasicResponse> createCompany(@Valid @RequestBody CreateCompanyRequest request) {
        Company company;

        try {
            company = companyService.createCompany(CompanyConverter.toEntity(request));
        } catch (Exception e) {
            log.error(e.toString());
            return new ResponseEntity<>(BasicResponse.fail(e.getMessage()), HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(BasicResponse.success(company));
    }

    @GetMapping
    public ResponseEntity<BasicResponse> getCompanies() {
        List<Company> companies;

        try {
            companies = companyService.getCompanies();
        } catch (Exception e) {
            log.error(e.toString());
            return new ResponseEntity<>(BasicResponse.fail(e.getMessage()), HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(BasicResponse.success(companies));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BasicResponse> getCompany(@PathVariable Long id) {
        Company company;

        try {
            company = companyService.getCompany(id);
        } catch (Exception e) {
            log.error(e.toString());
            return new ResponseEntity<>(BasicResponse.fail(e.getMessage()), HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(BasicResponse.success(company));
    }

    @PutMapping(consumes="application/json")
    public ResponseEntity<BasicResponse> updateCompany(@Valid @RequestBody UpdateCompanyRequest request) {
        Company company;

        try {
            company = companyService.updateCompany(CompanyConverter.toEntity(request));
        } catch (Exception e) {
            log.error(e.toString());
            return new ResponseEntity<>(BasicResponse.fail(e.getMessage()), HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(BasicResponse.success(company));
    }

    @DeleteMapping(consumes="application/json")
    public ResponseEntity<BasicResponse> deleteCompany(@Valid @RequestBody DeleteCompanyRequest request) {
        String result;

        try {
            result = companyService.deleteCompany(request.getId());
        } catch (Exception e) {
            log.error(e.toString());
            return new ResponseEntity<>(BasicResponse.fail(e.getMessage()), HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(BasicResponse.success(result));
    }
}
