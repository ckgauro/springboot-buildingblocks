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
public class UserMmDto {
    private Long id;
    private String username;
    private String firstname;
    private List<Order> orders;

}
