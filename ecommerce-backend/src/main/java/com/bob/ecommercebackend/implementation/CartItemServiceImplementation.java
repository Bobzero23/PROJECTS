package com.bob.ecommercebackend.implementation;

import com.bob.ecommercebackend.exception.CartItemException;
import com.bob.ecommercebackend.exception.UserException;
import com.bob.ecommercebackend.model.*;
import com.bob.ecommercebackend.repository.CartItemRepository;
import com.bob.ecommercebackend.repository.CartRepository;
import com.bob.ecommercebackend.service.CartItemService;
import com.bob.ecommercebackend.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartItemServiceImplementation implements CartItemService {
    private CartItemRepository cartItemRepository;
    private UserService userService;
    private CartRepository cartRepository;

    public CartItemServiceImplementation(CartItemRepository cartItemRepository, UserService userService, CartRepository cartRepository) {
        this.cartItemRepository = cartItemRepository;
        this.userService = userService;
        this.cartRepository = cartRepository;
    }


    @Override
    public CartItem createCartItem(CartItem cartItem) {
        cartItem.setQuantity(1);
        cartItem.setPrice(cartItem.getProduct().getPrice() * cartItem.getQuantity());
        cartItem.setDiscountedPrice(cartItem.getProduct().getDiscountedPrice() * cartItem.getQuantity());

        CartItem createdCartItem = cartItemRepository.save(cartItem);

        return createdCartItem;
    }

    @Override
    public CartItem updateCartItem(Long userId, Long id, CartItem cartItem) throws CartItemException, UserException {
        CartItem item = findCartItemById(id);
        User user = userService.findUserById(userId);

        if (user.getId().equals(userId)) {
            item.setQuantity(cartItem.getQuantity());
            item.setPrice(item.getQuantity() * item.getProduct().getPrice());
            item.setDiscountedPrice(item.getProduct().getDiscountedPrice() * item.getQuantity());
        }

        return cartItemRepository.save(item);
    }

    @Override
    public CartItem isCartItemExist(Cart cart, Product product, String  size, Long userId) {
        CartItem cartItem = cartItemRepository.isCartItemExist(cart, product, size, userId);
        return  cartItem;
        }

    @Override
    public void removeCartItem(Long userId, Long cartItemId) throws CartItemException, UserException {
        CartItem cartItem = findCartItemById(cartItemId);
        User user = userService.findUserById(cartItem.getId());
        User reqUser = userService.findUserById(userId);

        if (user.getId().equals(reqUser.getId())) {
            cartRepository.deleteById(cartItemId);
        }else  {
            throw new UserException("You can't remove another users item");
        }
    }

    @Override
    public CartItem findCartItemById(Long cartItemId) throws CartItemException {
        Optional<CartItem> optionalCartItem =  cartItemRepository.findById(cartItemId);

        if (optionalCartItem.isPresent()) {
            return optionalCartItem.get();
        }

        throw new CartItemException("We couldn't find cart item with id - " + cartItemId);
    }
}
