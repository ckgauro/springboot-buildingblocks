package com.gauro.restservices.springbootbuildingblocks.restservices.repositories;

import com.gauro.restservices.springbootbuildingblocks.restservices.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Chandra
 */
@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);
}
