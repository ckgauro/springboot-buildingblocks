package com.gauro.restservices.springbootbuildingblocks.restservices.exceptions;

/**
 * @author Chandra
 */
public class UserNotFoundException extends Exception{
    private static final long serialVersionUID = 7632008548076322454L;
    public UserNotFoundException(String message){
        super(message);
    }
}
