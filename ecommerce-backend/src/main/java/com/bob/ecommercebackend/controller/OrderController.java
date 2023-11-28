package com.bob.ecommercebackend.controller;

import com.bob.ecommercebackend.exception.OrderException;
import com.bob.ecommercebackend.exception.UserException;
import com.bob.ecommercebackend.model.Address;
import com.bob.ecommercebackend.model.Order;
import com.bob.ecommercebackend.model.User;
import com.bob.ecommercebackend.service.OrderService;
import com.bob.ecommercebackend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;
    private final UserService userService;

    public OrderController(OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    @PostMapping("/")
    public ResponseEntity<Order> createOrderHandler(@RequestBody Address shippingAddress,
                                                    @RequestHeader("Authorization") String jwt) throws UserException {
        User user = userService.findUserProfileByJwt(jwt);
        Order order = orderService.createOrder(user, shippingAddress);

        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }


    @GetMapping("/user")
    public ResponseEntity<List<Order>> usersOrderHistoryHandler(@RequestHeader("Authorization") String jwt) throws UserException{
        User user = userService.findUserProfileByJwt(jwt);
        List<Order> orders = orderService.usersOrderHistory(user.getId());

        return new ResponseEntity<>(orders, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> findOrderByIdHandler(@PathVariable Long id, @RequestHeader("Authorization") String jwt)
    throws UserException, OrderException {
        User user = userService.findUserProfileByJwt(jwt);
        Order order = orderService.findOrderById(user.getId());

        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }
}
