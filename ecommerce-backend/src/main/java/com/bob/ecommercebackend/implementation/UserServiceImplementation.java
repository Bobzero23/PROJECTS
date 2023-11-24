package com.bob.ecommercebackend.implementation;

import com.bob.ecommercebackend.config.JwtProvider;
import com.bob.ecommercebackend.exception.UserException;
import com.bob.ecommercebackend.model.User;
import com.bob.ecommercebackend.repository.UserRepository;
import com.bob.ecommercebackend.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserServiceImplementation implements UserService {
    private UserRepository userRepository;
    private JwtProvider jwtProvider;

    public UserServiceImplementation(UserRepository userRepository, JwtProvider jwtProvider) {
        this.userRepository = userRepository;
        this.jwtProvider = jwtProvider;
    }

    @Override
    public User findUserById(Long userId) throws UserException {
        Optional<User> user = userRepository.findById(userId);

        if (user.isPresent()) {
            return user.get();
        }

        throw new UserException("Couldn't find user with id - " + userId);
    }

    @Override
    public User findUserProfileByJwt(String jwt) throws UserException {
        String email = jwtProvider.getEmailFromToken(jwt);

        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new UserException("Couldn't find user with email - " + email);
        }

        return user;
    }
}
