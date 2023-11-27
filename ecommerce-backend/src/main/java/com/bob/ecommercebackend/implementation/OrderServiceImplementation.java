package com.bob.ecommercebackend.implementation;

import com.bob.ecommercebackend.exception.OrderException;
import com.bob.ecommercebackend.model.*;
import com.bob.ecommercebackend.repository.*;
import com.bob.ecommercebackend.service.CartService;
import com.bob.ecommercebackend.service.OrderItemService;
import com.bob.ecommercebackend.service.OrderService;
import com.bob.ecommercebackend.service.ProductService;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImplementation implements OrderService {
    private CartRepository cartRepository;
    private CartService cartItemService;
    private CartService cartService;
    private OrderItemRepository orderItemRepository;
    private OrderRepository orderRepository;
    private AddressRepository addressRepository;
    private OrderItemService orderItemService;
    private ProductService productService;
    private UserRepository userRepository;


    public OrderServiceImplementation(CartRepository cartRepository, CartService cartItemService, CartService cartService, OrderItemRepository orderItemRepository, OrderRepository orderRepository, AddressRepository addressRepository, OrderItemService orderItemService, ProductService productService, UserRepository userRepository) {
        this.cartRepository = cartRepository;
        this.cartItemService = cartItemService;
        this.cartService = cartService;
        this.orderItemRepository = orderItemRepository;
        this.orderRepository = orderRepository;
        this.addressRepository = addressRepository;
        this.orderItemService = orderItemService;
        this.productService = productService;
        this.userRepository = userRepository;
    }

    @Override
    public Order createOrder(User user, Address shippingAddress) {
        shippingAddress.setUser(user);
        Address address = addressRepository.save(shippingAddress);
        user.getAddress().add(address);
        userRepository.save(user);

        Cart cart = cartService.findUserCart(user.getId());

        List<OrderItem> orderItems = new ArrayList<>();

        for (CartItem item : cart.getCartItems()) {
            OrderItem orderItem = new OrderItem();

            orderItem.setPrice(item.getPrice());
            orderItem.setProduct(item.getProduct());
            orderItem.setQuantity(item.getQuantity());
            orderItem.setSize(item.getSize());
            orderItem.setUserId(item.getUserId());
            orderItem.setDiscountedPrice(item.getDiscountedPrice());

            OrderItem createdOrderItem = new OrderItem();
            orderItems.add(createdOrderItem);

        }

        Order createdOrder = new Order();
        createdOrder.setUser(user);
        createdOrder.setOrderItems(orderItems);
        createdOrder.setTotalPrice(cart.getTotalPrice());
        createdOrder.setTotalDiscountedPrice(cart.getTotalDiscountedPrice());
        createdOrder.setDiscount(cart.getDiscount());
        createdOrder.setTotalItem(cart.getTotalItem());
        createdOrder.setShippingAddress(shippingAddress);
        createdOrder.setOrderDate(LocalDateTime.now());
        createdOrder.setOrderStatus("PENDING");
        createdOrder.getPaymentDetails().setStatus("PENDING");
        createdOrder.setCreatedAt(LocalDateTime.now());


        Order savedOrder = orderRepository.save(createdOrder);

        for (OrderItem item: orderItems) {
            item.setOrder(savedOrder);
            orderItemRepository.save(item);
        }


        return null;
    }

    @Override
    public Order findOrderById(Long orderId) {
        return null;
    }

    @Override
    public List<Order> usersOrderHistory(Long userId) {
        return null;
    }

    @Override
    public Order placedOrder(Long orderId) throws OrderException {
        Order order = new Order();
        order.setOrderStatus("PLACED");
        order.getPaymentDetails().setStatus("COMPLETED");
        return order;
    }

    @Override
    public Order confirmedOrder(Long orderId) throws OrderException {
        Order order = findOrderById(orderId);

    }

    @Override
    public Order deliveredOrder(Long orderId) throws OrderException {
        return null;
    }

    @Override
    public Order canceledOrder(Long orderId) throws OrderException {
        return null;
    }

    @Override
    public Order shippedOrder(Long orderId) throws OrderException {
        return null;
    }

    @Override
    public List<Order> getAllOrders() {
        return null;
    }

    @Override
    public void deleteOrder(Long orderId) throws OrderException {

    }
}
