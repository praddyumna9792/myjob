package com.pixel7.firstjobapp.company.impl;

import com.pixel7.firstjobapp.company.Company;
import com.pixel7.firstjobapp.company.CompanyRepository;
import com.pixel7.firstjobapp.company.CompanyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {
    CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }


    @Override
    public Company getCompanyById(Long id) {
        return companyRepository.findById(id).orElse(null);
    }

    @Override
    public void createCompany(Company company) {
        companyRepository.save(company);
    }

    @Override
    public boolean delCompanyById(Long id) {
        try {
            if(companyRepository.existsById(id))
            {
                companyRepository.deleteById(id);
                return true;
            }
            else
                return false;

        }
        catch (Exception e)
        {
            return false;
        }
    }

    @Override
    public boolean updateCompanyById(Long id, Company updatedCompany) {
        Optional<Company> companyOptional = companyRepository.findById(id);
        if(companyOptional.isPresent())
        {
            Company company = companyOptional.get();
            company.setName(updatedCompany.getName());
            company.setDescription(updatedCompany.getDescription());
            company.setJobs(updatedCompany.getJobs());
            companyRepository.save(company);
            return true;
        }
        else
            return false;
    }
}
