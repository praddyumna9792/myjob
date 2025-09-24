package com.pixel7.firstjobapp.review;

import com.pixel7.firstjobapp.company.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByCompanyId(Long companyID);
}
