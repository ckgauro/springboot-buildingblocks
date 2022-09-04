package com.gauro.restservices.springbootbuildingblocks.restservices.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author Chandra
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="user")
@JsonIgnoreProperties({"firstname","lastname"})
public class User extends RepresentationModel<User> {
    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty(message="Username is Mandatory field. Please provide username")
    @Column(name = "USER_NAME", length = 50, nullable = false, unique = true)
    private String username;

    @Size(min=2, message = "FirstName should have at least 2 characters")
    @Column(name = "FIRST_NAME", length = 50, nullable = false)
    private String firstname;

    @Column(name = "LAST_NAME", length = 50, nullable = false)
    private String lastname;

    @Column(name = "EMAIL_ADDRESS", length = 50, nullable = false)
    private String email;

    @Column(name = "ROLE", length = 50, nullable = false)
    private String role;

    @Column(name = "SSN", length = 50, nullable = false, unique = true)
    @JsonIgnore
    private String ssn;

    //Here user is mapped By column name from Order table.
    @OneToMany(mappedBy = "user")
    private List<Order> orders;

    // No Argument Constructor

}
