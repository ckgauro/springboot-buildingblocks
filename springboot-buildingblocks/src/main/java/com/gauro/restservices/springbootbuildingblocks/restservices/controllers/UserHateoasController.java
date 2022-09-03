package com.gauro.restservices.springbootbuildingblocks.restservices.controllers;

import com.gauro.restservices.springbootbuildingblocks.restservices.entities.Order;
import com.gauro.restservices.springbootbuildingblocks.restservices.entities.User;
import com.gauro.restservices.springbootbuildingblocks.restservices.exceptions.UserNameNotFoundException;
import com.gauro.restservices.springbootbuildingblocks.restservices.exceptions.UserNotFoundException;
import com.gauro.restservices.springbootbuildingblocks.restservices.repositories.UserRepository;
import com.gauro.restservices.springbootbuildingblocks.restservices.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.Min;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * @author Chandra
 */

@RestController
@RequestMapping(value="/hateoas/users")
@Validated
public class UserHateoasController {

    @Autowired
    private  UserRepository userRepository;

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public EntityModel<User> getUserById(@PathVariable("id") @Min( 1) Long userid){

        try {
            Optional<User> userOptional=userService.getUserById(userid);
            if(userOptional.isEmpty()){
                throw  new UserNotFoundException("could not find user id :"+userid);
            }
            User user=userOptional.get();

            return EntityModel.of(user,
                    linkTo(methodOn(UserHateoasController.class).getUserById(userid)).withSelfRel(),
                    linkTo(methodOn(OrderHateoasController.class).getAllOrders(userid)).withRel("items"));


        } catch (UserNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }

    }
}
