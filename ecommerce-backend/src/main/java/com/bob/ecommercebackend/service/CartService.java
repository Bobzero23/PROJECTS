package com.bob.ecommercebackend.service;


import com.bob.ecommercebackend.exception.ProductException;
import com.bob.ecommercebackend.model.Cart;
import com.bob.ecommercebackend.model.User;
import com.bob.ecommercebackend.request.AddItemRequest;

public interface CartService {
    public Cart createCart(User user);
    public String addCartItem(Long userId, AddItemRequest request) throws ProductException;
    public Cart findUserCart(Long userId);
}
