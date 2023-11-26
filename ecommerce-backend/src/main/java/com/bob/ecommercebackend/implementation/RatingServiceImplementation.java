package com.bob.ecommercebackend.implementation;

import com.bob.ecommercebackend.exception.ProductException;
import com.bob.ecommercebackend.model.Product;
import com.bob.ecommercebackend.model.Rating;
import com.bob.ecommercebackend.model.User;
import com.bob.ecommercebackend.repository.RatingRepository;
import com.bob.ecommercebackend.request.RatingRequest;
import com.bob.ecommercebackend.service.ProductService;
import com.bob.ecommercebackend.service.RatingService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class    RatingServiceImplementation implements RatingService {
    private RatingRepository ratingRepository;
    private ProductService productService;

    public RatingServiceImplementation(RatingRepository ratingRepository, ProductService productService) {
        this.ratingRepository = ratingRepository;
        this.productService = productService;
    }

    @Override
    public Rating creatingRating(RatingRequest request, User user) throws ProductException {
        Product product = productService.findProductById(request.getProductId());

        Rating rating = new Rating();
        rating.setProduct(product);
        rating.setRating(request.getRating());
        rating.setUser(user);
        rating.setCreatedAt(LocalDateTime.now());
        return ratingRepository.save(rating);
    }

    @Override
    public List<Rating> getProductsRating(Long productId) {
        return ratingRepository.getAllProductsRating(productId);
    }
}
