package com.bob.ecommercebackend.controller;

import com.bob.ecommercebackend.exception.CartItemException;
import com.bob.ecommercebackend.exception.UserException;
import com.bob.ecommercebackend.model.CartItem;
import com.bob.ecommercebackend.model.User;
import com.bob.ecommercebackend.response.ApiResponse;
import com.bob.ecommercebackend.service.CartItemService;
import com.bob.ecommercebackend.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart_items")
public class CartItemController {
    private final UserService userService;
    private final CartItemService cartItemService;

    public CartItemController(UserService userService, CartItemService cartItemService) {
        this.userService = userService;
        this.cartItemService = cartItemService;
    }

    @DeleteMapping("/cartItemId")
    public ResponseEntity<ApiResponse> deleteCartItem(@PathVariable Long cartItemId, @RequestHeader("Authorization") String jwt)
        throws UserException, CartItemException {
        User user = userService.findUserProfileByJwt(jwt);
        cartItemService.removeCartItem(user.getId(), cartItemId);

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Item removed successfully");
        apiResponse.setStatus(true);

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }


    @PutMapping("/{cartItemId}")
    public ResponseEntity<CartItem> updateCartItem(@RequestBody CartItem cartItem, @PathVariable Long cartItemId,
                                                   @RequestHeader("Authorization") String jwt) throws UserException, CartItemException {
        User user = userService.findUserProfileByJwt(jwt);
        CartItem updateCartItem = cartItemService.updateCartItem(user.getId(), cartItemId, cartItem);
        return new ResponseEntity<>(updateCartItem, HttpStatus.OK);
    }

}
