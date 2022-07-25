package com.gauro.restservices.springbootbuildingblocks.restservices.controllers;

import com.gauro.restservices.springbootbuildingblocks.restservices.entities.User;
import com.gauro.restservices.springbootbuildingblocks.restservices.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @author Chandra
 */
@RestController
public class UserController {

    //Autowired the UserService
    @Autowired
    private UserService userService;

    //getAllUsers Method
    @GetMapping("/users")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    //create User method
    //@RequestBody Annotation
    //@PostMapping Annotation
    @PostMapping("/users")
    public User createUser(@RequestBody User user){
        return userService.createUser(user);
    }

    //getUserById
    @GetMapping("/users/{id}")
    public Optional<User> getUserId(@PathVariable("id") Long id){
        return userService.getUserById(id);
    }

    //updateUserById
    @PutMapping("/users/{id}")
    public User updateUserById(@PathVariable("id") Long id, @RequestBody User user){
        return userService.updateUserById(id, user);
    }

    //deleteUserById
    @DeleteMapping("/users/{id}")
    public void deleteUserById(@PathVariable("id") Long id){
        userService.deleteUserById(id);

    }

    //getUserByUsername
    @GetMapping("/users/byusername/{username}")
    public User getUserByUsername(@PathVariable("username") String username){
        return userService.getUserByUsername(username);
    }




}
