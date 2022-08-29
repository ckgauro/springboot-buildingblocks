package com.gauro.restservices.springbootbuildingblocks.restservices.exceptions;

/**
 * @author Chandra
 */
public class UserNameNotFoundException extends Exception{


    private static final long serialVersionUID = 3067877559878195521L;
    public UserNameNotFoundException(String message){
        super(message);
    }
}
