package com.gauro.restservices.springbootbuildingblocks.restservices.controllers;

import com.gauro.restservices.springbootbuildingblocks.restservices.entities.Order;
import com.gauro.restservices.springbootbuildingblocks.restservices.entities.User;
import com.gauro.restservices.springbootbuildingblocks.restservices.exceptions.UserNotFoundException;
import com.gauro.restservices.springbootbuildingblocks.restservices.repositories.OrderRepository;
import com.gauro.restservices.springbootbuildingblocks.restservices.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @author Chandra
 */
@Slf4j
@RestController
@RequestMapping(value="/users")
public class OrderController {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/{userid}/orders")
    public List<Order> getAllOrders(@PathVariable Long userid) throws UserNotFoundException {

        Optional<User> userOptional=userRepository.findById(userid);
        if(userOptional.isEmpty()){
            throw new UserNotFoundException("User not Found!!!");
        }
        return userOptional.get().getOrders();
    }

    @PostMapping("{userid}/orders")
    public Order createOrder(@PathVariable Long userid, @RequestBody Order order) throws UserNotFoundException {
        log.info("userid for Order ===>"+userid);

        Optional<User> userOptional=userRepository.findById(userid);
        log.info("userOptional for Order ===>"+userOptional.isPresent());
        if(userOptional.isEmpty()){
            throw new UserNotFoundException("User not found !!!!");
        }
        User user=userOptional.get();
        order.setUser(user);
        return orderRepository.save(order);
    }
    @GetMapping("/{userid}/orders/{orderId}")
    public Order getOrderByOrderId(@PathVariable Long userid, @PathVariable Long orderId) throws UserNotFoundException {

        Optional<Order> orderOptional=orderRepository.findById(orderId);

        if(orderOptional.isEmpty()){
            throw new UserNotFoundException("User not found !!! :(");
        }
         return orderOptional.get();

    }
}
