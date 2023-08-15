package com.OnlineBookStore.controllers;

import com.OnlineBookStore.models.Review;
import com.OnlineBookStore.services.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReviewController {

    private  final ReviewService reviewService;


    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/addReview")
    public ResponseEntity<Review> addReview(@RequestBody Review review) {
        reviewService.saveReview(review);
        return ResponseEntity.ok(review);
    }
}
