package com.bob.ecommercebackend.controller;

import com.bob.ecommercebackend.exception.ProductException;
import com.bob.ecommercebackend.exception.UserException;
import com.bob.ecommercebackend.model.Rating;
import com.bob.ecommercebackend.model.User;
import com.bob.ecommercebackend.request.RatingRequest;
import com.bob.ecommercebackend.service.RatingService;
import com.bob.ecommercebackend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ratings")
public class RatingController {
    private final UserService userService;
    private final RatingService ratingService;

    public RatingController(UserService userService, RatingService ratingService) {
        this.userService = userService;
        this.ratingService = ratingService;
    }

    @PostMapping("/create")
    public ResponseEntity<Rating> creatRatingHandler(@RequestParam RatingRequest request, @RequestHeader("Authorization") String jwt)
    throws UserException, ProductException {
        User user = userService.findUserProfileByJwt(jwt);
        Rating rating = ratingService.creatingRating(request, user);

        return new ResponseEntity<>(rating, HttpStatus.CREATED);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<Rating>> getProductRatingHandler(@PathVariable Long productId, @RequestHeader("Authorization") String jwt)
    throws UserException, ProductException {
        User user = userService.findUserProfileByJwt(jwt);
        List<Rating> ratings = ratingService.getProductsRating(productId);

        return new ResponseEntity<>(ratings, HttpStatus.ACCEPTED);
    }


}
