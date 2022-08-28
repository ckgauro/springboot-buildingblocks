package com.gauro.restservices.springbootbuildingblocks.restservices.services;

import com.gauro.restservices.springbootbuildingblocks.restservices.entities.User;
import com.gauro.restservices.springbootbuildingblocks.restservices.exceptions.UserExistsException;
import com.gauro.restservices.springbootbuildingblocks.restservices.exceptions.UserNotFoundException;
import com.gauro.restservices.springbootbuildingblocks.restservices.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

/**
 * @author Chandra
 */
@Service
public class UserService {

    //Autowired the UserRepository
    @Autowired
    private UserRepository userRepository;

    //getAllUsers Method
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    //CreateUser Method
    public User createUser(User user) throws UserExistsException {
        //if user exist using username
        User existingUser=userRepository.findByUsername(user.getUsername());
        //if not exists throw UserExistsException
        if(existingUser!=null){
            throw new UserExistsException("User already exists in repository");
        }
        return userRepository.save(user);
    }


    //getUserByID
    public Optional<User> getUserById(Long id) throws UserNotFoundException {
        Optional<User> user=userRepository.findById(id);
        if(user.isEmpty()){
            throw new UserNotFoundException("User not found in user Repository");
        }

        return user;
    }
    //updateUserById
    public User updateUserById(Long id, User user) throws UserNotFoundException {
        Optional<User> optionalUser=userRepository.findById(id);
        if(optionalUser.isEmpty()){
            throw new UserNotFoundException(("User not found in user Repository, provide the correct user id"));
        }
        user.setId(id);
        return userRepository.save(user);
    }
    //deleteUserById
    public void deleteUserById(Long id){

        if(userRepository.findById(id).isPresent()){
            userRepository.deleteById(id);
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"User Not found in user Repository , provide the correct usedr id");
    }

    //getUserByUsername
    public User getUserByUsername(String username){
        return userRepository.findByUsername(username);
    }

}
