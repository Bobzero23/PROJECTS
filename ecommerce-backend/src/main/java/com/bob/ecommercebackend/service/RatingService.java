package com.bob.ecommercebackend.service;

import com.bob.ecommercebackend.exception.ProductException;
import com.bob.ecommercebackend.model.Rating;
import com.bob.ecommercebackend.model.User;
import com.bob.ecommercebackend.request.RatingRequest;

import java.util.List;

public interface RatingService {
    public Rating creatingRating(RatingRequest request, User user) throws ProductException;
    public List<Rating> getProductsRating(Long productId);
}
