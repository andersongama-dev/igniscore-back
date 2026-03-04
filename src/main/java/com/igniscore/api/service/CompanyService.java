package com.igniscore.api.service;

import com.igniscore.api.model.Company;
import com.igniscore.api.repository.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {
    private final CompanyRepository repository;

    public CompanyService(CompanyRepository repository) {
        this.repository = repository;
    }

    public List<Company> findAll() {
        return repository.findAll();
    }

    public Company create(String name, String cnpj, String ie, String ufIe, String email, String phone) {
        Company company = new Company();
        company.setName(name);
        company.setCnpj(cnpj);
        company.setIe(ie);
        company.setUfIe(ufIe);
        company.setEmail(email);
        company.setPhone(phone);

        return repository.save(company);
    }
}
