package com.bob.ecommercebackend.controller;

import com.bob.ecommercebackend.exception.ProductException;
import com.bob.ecommercebackend.exception.UserException;
import com.bob.ecommercebackend.model.Cart;
import com.bob.ecommercebackend.model.User;
import com.bob.ecommercebackend.request.AddItemRequest;
import com.bob.ecommercebackend.response.ApiResponse;
import com.bob.ecommercebackend.service.CartService;
import com.bob.ecommercebackend.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
@Tag(name = "Cart Management", description = "find user cart, add item to cart")
public class CartController {
    private final CartService cartService;
    private final UserService userService;

    public CartController(CartService cartService, UserService userService) {
        this.cartService = cartService;
        this.userService = userService;
    }

    @GetMapping("/")
    @Operation(description = "find item by user id")
    public ResponseEntity<Cart> findUserCartHandler(@RequestHeader("Authorization") String jwt) throws UserException {
        User user = userService.findUserProfileByJwt(jwt);
        Cart cart = cartService.findUserCart(user.getId());

        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @PutMapping("/add")
    @Operation(description = "add item to cart")
    public ResponseEntity<ApiResponse> addItemToCartHandler(@RequestBody AddItemRequest request,
                                                            @RequestHeader("Authorization") String jwt)
            throws ProductException, UserException {
        User user = userService.findUserProfileByJwt(jwt);
        cartService.addCartItem(user.getId(), request);

        ApiResponse response = new ApiResponse();
        response.setStatus(true);
        response.setMessage("Item added to cart successfully");

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
