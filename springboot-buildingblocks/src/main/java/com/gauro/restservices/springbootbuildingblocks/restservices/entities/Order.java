package com.gauro.restservices.springbootbuildingblocks.restservices.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;

/**
 * @author Chandra
 */
@Data
@Entity
@Table(name="orders")
public class Order extends RepresentationModel<Order> {

    @Id
    @GeneratedValue
    @JsonView(Views.Internal.class)
    private Long orderId;

    @JsonView(Views.Internal.class)
    private String orderDescription;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User user;


}
