package com.example.crm.service;

import com.example.crm.entity.Company;
import com.example.crm.exception.BizException;
import com.example.crm.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
@CacheConfig(cacheNames = "Company")
public class CompanyService {

    @Autowired
    CompanyRepository companyRepo;

    public List<Company> getCompanies() {
        return companyRepo.findAll();
    }

    @Cacheable(key = "#id")
    public Company getCompany(Long id) {
        return companyRepo.findById(id)
                .orElseThrow(() -> new BizException("Not found company"));
    }

    public Company createCompany(Company company) {
        return companyRepo.save(company);
    }

    public List<Company> createCompanies(List<Company> companies) {
        return companyRepo.saveAll(companies);
    }

    @CachePut(key = "#company.id")
    public Company updateCompany(Company company) {
        Company currentCompany = companyRepo.findById(company.getId())
                .orElseThrow(() -> new BizException("Not found this company"));
        currentCompany.setAddress(company.getAddress());
        currentCompany.setName(company.getName());
        currentCompany.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        currentCompany.setUpdatedBy("aaa");

        return companyRepo.save(currentCompany);
    }

    @CacheEvict(key = "#id")
    public String deleteCompany(Long id) {
        Company company = companyRepo.getById(id);
        companyRepo.delete(company);

        return "deleted";
    }
}
