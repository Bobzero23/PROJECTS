package com.bob.ecommercebackend.controller;

import com.bob.ecommercebackend.exception.ProductException;
import com.bob.ecommercebackend.exception.UserException;
import com.bob.ecommercebackend.model.Review;
import com.bob.ecommercebackend.model.User;
import com.bob.ecommercebackend.request.ReviewRequest;
import com.bob.ecommercebackend.service.ReviewService;
import com.bob.ecommercebackend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
    private final ReviewService reviewService;
    private final UserService userService;

    public ReviewController(ReviewService reviewService, UserService userService) {
        this.reviewService = reviewService;
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<Review> createReviewHandler(@RequestBody ReviewRequest request, @RequestHeader("Authorization") String jwt)
    throws UserException, ProductException {
        User user = userService.findUserProfileByJwt(jwt);
        Review review = reviewService.createReview(request, user);

        return new ResponseEntity<>(review, HttpStatus.CREATED);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<Review>> getProductReviewsHandler(@PathVariable Long productId) throws UserException, ProductException{
        List<Review> reviews = reviewService.getAllReview(productId);

        return new ResponseEntity<>(reviews, HttpStatus.CREATED);
    }
}
