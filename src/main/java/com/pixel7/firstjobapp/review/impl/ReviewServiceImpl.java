package com.pixel7.firstjobapp.review.impl;

import com.pixel7.firstjobapp.company.Company;
import com.pixel7.firstjobapp.company.CompanyService;
import com.pixel7.firstjobapp.review.Review;
import com.pixel7.firstjobapp.review.ReviewRepository;
import com.pixel7.firstjobapp.review.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final CompanyService companyService;

    public ReviewServiceImpl(ReviewRepository reviewRepository, CompanyService companyService) {
        this.reviewRepository = reviewRepository;
        this.companyService = companyService;
    }

    @Override
    public List<Review> getAllReviews(Long companyID) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyID);
        return reviews;
    }

    @Override
    public Review getReview(Long companyId, Long reviewId) {
       List<Review> reviews =  reviewRepository.findByCompanyId(companyId);
       return reviews.stream().filter(review -> review.getId().equals(reviewId)).findFirst().orElse(null);
    }

    @Override
    public boolean addReview(Long companyId, Review review) {
        Company company = companyService.getCompanyById(companyId);
        if (company != null) {
            review.setCompany(company);
            reviewRepository.save(review);
            return true;
        } else
            return false;
    }

    @Override
    public boolean deleteReviewById(Long companyId, Long reviewId) {
        try {
           if(companyService.getCompanyById(companyId) != null && reviewRepository.existsById(reviewId))
           {
               Review review = reviewRepository.findById(reviewId).orElse(null);
               Company company = review.getCompany();
               company.getReviews().remove(review);
               review.setCompany(null);
               companyService.updateCompanyById(companyId, company);
               reviewRepository.deleteById(reviewId);
               return true;
           }
           else
               return false;

        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean updateReview(Long companyId, Long reviewId, Review updatedReview) {
        if(companyService.getCompanyById(companyId) != null)
        {
            updatedReview.setCompany(companyService.getCompanyById(companyId));
            updatedReview.setId(reviewId);
            reviewRepository.save(updatedReview);
            return true;
        }
        else
            return false;
    }


}
