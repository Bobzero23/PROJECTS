package com.bob.ecommercebackend.service;


import com.bob.ecommercebackend.exception.OrderException;
import com.bob.ecommercebackend.model.Address;
import com.bob.ecommercebackend.model.Order;
import com.bob.ecommercebackend.model.User;

import java.util.List;

public interface OrderService {
    public Order createOrder(User user, Address shippingAddress);
    public Order findOrderById(Long orderId);
    public List<Order> usersOrderHistory(Long userId);
    public Order placedOrder(Long orderId) throws OrderException;
    public Order confirmedOrder(Long orderId) throws OrderException;
    public Order deliveredOrder(Long orderId) throws OrderException;
    public Order canceledOrder(Long orderId) throws OrderException;
    public Order shippedOrder(Long orderId) throws OrderException;
    public List<Order> getAllOrders();
    public void deleteOrder(Long orderId) throws    OrderException;


}
