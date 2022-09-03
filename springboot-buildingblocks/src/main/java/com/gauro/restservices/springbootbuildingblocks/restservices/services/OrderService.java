package com.gauro.restservices.springbootbuildingblocks.restservices.services;

import com.gauro.restservices.springbootbuildingblocks.restservices.entities.Order;
import com.gauro.restservices.springbootbuildingblocks.restservices.repositories.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Chandra
 */
@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order getOrderByOrderId(Long orderid) throws Exception {
        Optional<Order>  order= orderRepository.findById(orderid);
        if(order.isEmpty()){
            throw new Exception(orderid+" could not find in repository :(");
        }
        return order.get();
    }

}
