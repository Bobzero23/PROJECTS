package com.bob.ecommercebackend.service;

import com.bob.ecommercebackend.exception.ProductException;
import com.bob.ecommercebackend.model.Review;
import com.bob.ecommercebackend.model.User;
import com.bob.ecommercebackend.request.ReviewRequest;

import java.util.List;

public interface ReviewService {
    public Review createReview(ReviewRequest request, User user) throws ProductException;
    public List<Review> getAllReview(Long productId);
}
