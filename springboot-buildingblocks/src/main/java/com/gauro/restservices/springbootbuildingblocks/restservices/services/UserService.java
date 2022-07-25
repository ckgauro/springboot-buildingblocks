package com.gauro.restservices.springbootbuildingblocks.restservices.services;

import com.gauro.restservices.springbootbuildingblocks.restservices.entities.User;
import com.gauro.restservices.springbootbuildingblocks.restservices.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public User createUser(User user){
        return userRepository.save(user);
    }

    //getUserByID
    public Optional<User> getUserById(Long id){
        return userRepository.findById(id);
    }
    //updateUserById
    public User updateUserById(Long id, User user){
        user.setId(id);
        return userRepository.save(user);
    }
    //deleteUserById
    public void deleteUserById(Long id){
        if(userRepository.findById(id).isPresent()){
            userRepository.deleteById(id);
        }
    }

    //getUserByUsername
    public User getUserByUsername(String username){
        return userRepository.findByUsername(username);
    }



}
