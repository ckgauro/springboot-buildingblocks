package com.gauro.restservices.springbootbuildingblocks.restservices.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

/**
 * @author Chandra
 */
@Data
@Entity
@Table(name="orders")
public class Order {

    @Id
    @GeneratedValue
    private Long orderId;


    private String orderDescription;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User user;


}
