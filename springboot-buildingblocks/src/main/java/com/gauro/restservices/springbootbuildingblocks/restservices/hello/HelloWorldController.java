package com.gauro.restservices.springbootbuildingblocks.restservices.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

/**
 * @author Chandra
 */
@RestController
public class HelloWorldController {

    @Autowired
    private ResourceBundleMessageSource messageSource;

    @GetMapping("/helloworld")
    public String helloWorld(){
        return "Hello World";
    }


    @GetMapping("/helloworld1")
    public String helloWorld1(){
        return "Hello World1";
    }

    @GetMapping("/helloworld-bean")
    public UserDetails helloWorldBean(){
        return UserDetails.builder()
                .firstName("Mina")
                .city("KTM")
                .lastName("Reddy")
                .build();
    }

    @GetMapping("/hello-int")
    public  String getMessageInt18NFormat(@RequestHeader(name="Accept-language", required = false) String local){
        return messageSource.getMessage("label.hello",null, new Locale(local));
    }
    @GetMapping("/hello-int2")
    public String getMessagesInI18NFormat2() {
        return messageSource.getMessage("label.hello", null, LocaleContextHolder.getLocale());

    }

}
