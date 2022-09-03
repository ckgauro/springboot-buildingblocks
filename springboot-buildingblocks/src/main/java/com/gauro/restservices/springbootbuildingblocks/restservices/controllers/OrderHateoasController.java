package com.gauro.restservices.springbootbuildingblocks.restservices.controllers;

import com.gauro.restservices.springbootbuildingblocks.restservices.entities.Order;
import com.gauro.restservices.springbootbuildingblocks.restservices.entities.User;
import com.gauro.restservices.springbootbuildingblocks.restservices.exceptions.UserNotFoundException;
import com.gauro.restservices.springbootbuildingblocks.restservices.repositories.OrderRepository;
import com.gauro.restservices.springbootbuildingblocks.restservices.repositories.UserRepository;
import com.gauro.restservices.springbootbuildingblocks.restservices.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


/**
 * @author Chandra
 */
@Slf4j
@RestController
@RequestMapping(value="/hateoas/users")
public class OrderHateoasController {

    @Autowired
    private UserService userService;

    @Autowired
    private OrderRepository orderRepository;

    //get All Orders for a user

    @GetMapping("/{userid}/orders")
    public CollectionModel<Order> getAllOrders(@PathVariable Long userid) throws UserNotFoundException {
        log.info("=================>10 userid=>"+userid);
        Optional<User> user=userService.getUserById(userid);
        if(user.isEmpty()){
            throw new UserNotFoundException("User is not found");
        }

        List<Order> orders=user.get().getOrders();
        log.info("=================>20 orders=>"+orders.size());
        orders.forEach(order -> {
            try {
                order.add(linkTo(methodOn(OrderController.class).getOrderByOrderId(userid,order.getOrderId())).withSelfRel());
            } catch (UserNotFoundException e) {
                throw new RuntimeException(e);
            }
            // order.add(linkTo(methodOn(OrderController.class).getDirectorMovies(director.getId())).withRel("directorMovies"));
        });
        Link link = linkTo(methodOn(OrderHateoasController.class).getAllOrders(userid)).withSelfRel();
//        Link link = linkTo(methodOn(OrderHateoasController.class).userService.getUserById(userid))
//                .withSelfRel();
      //  Link link = linkTo(OrderHateoasController.class).slash(getAllOrders(1L)).withSelfRel();

        CollectionModel<Order> result=CollectionModel.of(orders,link);
        return result;

    }


//
//    @GetMapping("/{userid}/orders")
//    public Resources<Order> getAllOrders(@PathVariable Long userid) throws UserNotFoundException {
//        Optional<User> optionalUser=userRepository.findById(userid);
//        if(optionalUser.isEmpty()){
//            throw new UserNotFoundException("User Not found !!!");
//        }
//        List<Order> allOrders=optionalUser.get().getOrders();
//        Resources<Order> finalResources=new Resources()<Order>(allOrders);
//        return  finalResources;
//
//    }
}
