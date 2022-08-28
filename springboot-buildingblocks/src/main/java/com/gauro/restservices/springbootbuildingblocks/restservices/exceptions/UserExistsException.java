package com.gauro.restservices.springbootbuildingblocks.restservices.exceptions;

/**
 * @author Chandra
 */
public class UserExistsException extends Exception{

    /**
     *
     */

    private static final long serialVersionUID = 8520884058786939282L;

    public UserExistsException(String message){
        super(message);
    }

}
