package com.gauro.restservices.springbootbuildingblocks.restservices.repositories;

import com.gauro.restservices.springbootbuildingblocks.restservices.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Chandra
 */
@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
}
