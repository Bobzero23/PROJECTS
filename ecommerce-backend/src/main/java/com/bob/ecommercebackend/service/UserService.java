package com.bob.ecommercebackend.service;

import com.bob.ecommercebackend.exception.UserException;
import com.bob.ecommercebackend.model.User;


public interface UserService {
    public User findUserById(Long userId) throws UserException;
    public User findUserProfileByJwt(String jwt) throws UserException;
}
