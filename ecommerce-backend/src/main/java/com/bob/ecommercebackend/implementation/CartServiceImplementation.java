package com.bob.ecommercebackend.implementation;

import com.bob.ecommercebackend.exception.ProductException;
import com.bob.ecommercebackend.model.Cart;
import com.bob.ecommercebackend.model.User;
import com.bob.ecommercebackend.repository.CartRepository;
import com.bob.ecommercebackend.request.AddItemRequest;
import com.bob.ecommercebackend.service.CartItemService;
import com.bob.ecommercebackend.service.CartService;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImplementation implements CartService {
    private CartRepository cartRepository;
    private CartItemService cartItemService;

    @Override
    public Cart createCart(User user) {
        return null;
    }

    @Override
    public String addCartItem(Long userId, AddItemRequest request) throws ProductException {
        return null;
    }

    @Override
    public Cart findUserCart(Long userId) {
        return null;
    }
}
