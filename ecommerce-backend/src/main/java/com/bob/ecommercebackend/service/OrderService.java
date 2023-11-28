package com.bob.ecommercebackend.service;


import com.bob.ecommercebackend.exception.OrderException;
import com.bob.ecommercebackend.model.Address;
import com.bob.ecommercebackend.model.Order;
import com.bob.ecommercebackend.model.User;

import java.util.List;

public interface OrderService {
    Order createOrder(User user, Address shippingAddress);
    Order findOrderById(Long orderId) throws OrderException;
    List<Order> usersOrderHistory(Long userId);
    Order placedOrder(Long orderId) throws OrderException;
    Order confirmedOrder(Long orderId) throws OrderException;
    Order deliveredOrder(Long orderId) throws OrderException;
    Order canceledOrder(Long orderId) throws OrderException;
    Order shippedOrder(Long orderId) throws OrderException;
    List<Order> getAllOrders();
    void deleteOrder(Long orderId) throws    OrderException;


}
