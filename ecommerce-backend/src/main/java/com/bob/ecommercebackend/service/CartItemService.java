package com.bob.ecommercebackend.service;

import com.bob.ecommercebackend.exception.CartItemException;
import com.bob.ecommercebackend.exception.UserException;
import com.bob.ecommercebackend.model.Cart;
import com.bob.ecommercebackend.model.CartItem;
import com.bob.ecommercebackend.model.Product;
import com.bob.ecommercebackend.model.Size;

public interface CartItemService {
    public CartItem createCartItem(CartItem cartItem);
    public CartItem updateCartItem(Long userId, Long id, CartItem cartItem) throws CartItemException, UserException;
    public CartItem isCartItemExist(Cart cart, Product product, String size, Long userId);
    public void removeCartItem(Long userId, Long cartItemId) throws CartItemException, UserException;
    public CartItem findCartItemById(Long cartItemId) throws CartItemException;

}
