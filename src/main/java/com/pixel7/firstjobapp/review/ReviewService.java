package com.pixel7.firstjobapp.review;

import java.util.List;

public interface ReviewService {
    List<Review> getAllReviews(Long companyID);

    Review getReview(Long companyId, Long reviewId);

    boolean addReview(Long CompanyId, Review review);

    boolean deleteReviewById(Long companyId, Long reviewId);

    boolean updateReview(Long companyId, Long reviewId,Review review);
}
