package com.bob.ecommercebackend.implementation;

import com.bob.ecommercebackend.model.OrderItem;
import com.bob.ecommercebackend.repository.OrderItemRepository;
import com.bob.ecommercebackend.service.OrderItemService;
import org.springframework.stereotype.Service;

@Service
public class OrderItemServiceImplementation implements OrderItemService {
    private OrderItemRepository orderItemRepository;

    public OrderItemServiceImplementation(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    @Override
    public OrderItem createOrderItem(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }
}
