package com.gauro.restservices.springbootbuildingblocks.restservices.hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Chandra
 */
@RestController
public class HelloWorldController {

    @GetMapping("/helloworld")
    public String helloWorld(){
        return "Hello World";
    }

    @GetMapping("/helloworld-bean")
    public UserDetails helloWorldBean(){
        return UserDetails.builder()
                .firstName("Mina")
                .city("KTM")
                .lastName("Reddy")
                .build();
    }

}
