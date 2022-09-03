package com.gauro.restservices.springbootbuildingblocks.restservices.controllers;

import com.gauro.restservices.springbootbuildingblocks.restservices.entities.Order;
import com.gauro.restservices.springbootbuildingblocks.restservices.entities.User;
import com.gauro.restservices.springbootbuildingblocks.restservices.exceptions.UserNotFoundException;
import com.gauro.restservices.springbootbuildingblocks.restservices.repositories.OrderRepository;
import com.gauro.restservices.springbootbuildingblocks.restservices.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @author Chandra
 */
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

        Optional<User> userOptional=userRepository.findById(userid);
        if(userOptional.isEmpty()){
            throw new UserNotFoundException("User not found !!!!");
        }
        User user=userOptional.get();
        order.setUser(user);
        return orderRepository.save(order);
    }
}
