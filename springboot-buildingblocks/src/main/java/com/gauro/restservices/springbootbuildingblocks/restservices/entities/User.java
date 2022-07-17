package com.gauro.restservices.springbootbuildingblocks.restservices.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author Chandra
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="user")
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name="USER_NAME", length = 50, nullable = false, unique = true)
    private String username;

    @Column(name="FIRST_NAME", length = 50, nullable = false)
    private String firstName;

    @Column(name="LAST_NAME", length = 50, nullable = false)
    private String lastName;

    @Column(name = "EMAIl_ADDRESS", length = 50, nullable = false)
    private String email;

    @Column(name = "ROLE", length = 50, nullable = false)
    private String role;

    @Column(name="ssn", length = 50, nullable = false, unique = true)
    private String ssn;

}
