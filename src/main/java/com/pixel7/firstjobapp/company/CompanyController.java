package com.pixel7.firstjobapp.company;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    private CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public ResponseEntity<List<Company>> findAll()
    {
        return ResponseEntity.ok(companyService.getAllCompanies()) ;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable Long id)
    {
        Company company = companyService.getCompanyById(id);
        if(company != null)
            return new ResponseEntity<>(company, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<String> addCompany(@RequestBody Company company)
    {
        companyService.createCompany(company);
        return new ResponseEntity<>("Company Added Successfully",HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCompanyById(@PathVariable Long id)
    {
        boolean isDeleted = companyService.delCompanyById(id);
        if(isDeleted)
            return new ResponseEntity<>("Company Deleted Successfully", HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCompanyById(@PathVariable Long id, @RequestBody Company updatedCompany)
    {
        boolean isUpdated = companyService.updateCompanyById(id, updatedCompany);
        if(isUpdated)
            return  new ResponseEntity<>("Company Updated Successfully", HttpStatus.OK);
        else
            return new ResponseEntity<>("Company Updated Successfully", HttpStatus.NOT_FOUND);
    }
}
