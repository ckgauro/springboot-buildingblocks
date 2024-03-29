package com.gauro.restservices.springbootbuildingblocks.restservices.controllers;

import com.gauro.restservices.springbootbuildingblocks.restservices.entities.User;
import com.gauro.restservices.springbootbuildingblocks.restservices.exceptions.UserExistsException;
import com.gauro.restservices.springbootbuildingblocks.restservices.exceptions.UserNameNotFoundException;
import com.gauro.restservices.springbootbuildingblocks.restservices.exceptions.UserNotFoundException;
import com.gauro.restservices.springbootbuildingblocks.restservices.services.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.Optional;

/**
 * @author Chandra
 */
@Api(tags = "User Management RESTful Services", value = "UserCotroller", description = "Controller for User Management Service" )
@Slf4j
@Validated
@RestController
@RequestMapping(value="/users")
public class UserController {

    //Autowired the UserService
    @Autowired
    private UserService userService;

    //getAllUsers Method
   // @GetMapping("/users")
    @ApiOperation(value = "Retrieve list of users")
    @GetMapping
    public List<User> getAllUsers()
    {
        log.info("GetAll users====>");

        return userService.getAllUsers();
    }

    //create User method
    //@RequestBody Annotation
    //@PostMapping Annotation
   // @PostMapping("/users")
    @ApiOperation(value = "Creates a new user")
    @PostMapping
    public ResponseEntity<Void> createUser(@ApiParam("User information for a new user to be created.") @Valid @RequestBody User user , UriComponentsBuilder builder){
        try{
            userService.createUser(user);
            HttpHeaders headers=new HttpHeaders();
            headers.setLocation(builder.path("/user/{id}").buildAndExpand(user.getId()).toUri());
            return new ResponseEntity<Void>(headers, HttpStatus.CREATED);

        }catch (UserExistsException ex){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }

    }

    //getUserById
    //@GetMapping("/users/{id}")
    @GetMapping("/{id}")
    public Optional<User> getUserId(@PathVariable("id") @Min(1) Long id){
        try {
            return userService.getUserById(id);
        } catch (UserNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,ex.getMessage());
        }
    }

    //updateUserById
   // @PutMapping("/users/{id}")
    @PutMapping("/{id}")
    public User updateUserById(@PathVariable("id") Long id, @RequestBody User user){
        try {
            return userService.updateUserById(id, user);
        } catch (UserNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }

    //deleteUserById
    //@DeleteMapping("/users/{id}")
    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable("id") Long id){
        userService.deleteUserById(id);

    }

    //getUserByUsername
   // @GetMapping("/users/byusername/{username}")
    @GetMapping("/byusername/{username}")
    public User getUserByUsername(@PathVariable("username") String username) throws UserNameNotFoundException {
        User user=userService.getUserByUsername(username);
        if(user==null){
            throw new UserNameNotFoundException("UserName :'"+username+"' not found in useer Repository");
           // throw new UserName
        }
        return userService.getUserByUsername(username);
    }




}
