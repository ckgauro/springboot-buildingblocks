package com.gauro.restservices.springbootbuildingblocks.restservices.dtos;

import com.gauro.restservices.springbootbuildingblocks.restservices.entities.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Chandra
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDtoV2 {
    private Long userid;
    private String username;
    private String firstname;
    private String lastname;
    private String email;
    private String role;
    private String ssn;
    private List<Order> orders;
    private String address;
}
