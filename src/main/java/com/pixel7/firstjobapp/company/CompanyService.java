package com.pixel7.firstjobapp.company;

import java.util.List;

public interface CompanyService {
    List<Company> getAllCompanies();

    Company getCompanyById(Long id);

    void createCompany(Company company);

    boolean delCompanyById(Long id);

    boolean updateCompanyById(Long id, Company updatedCompany);
}
