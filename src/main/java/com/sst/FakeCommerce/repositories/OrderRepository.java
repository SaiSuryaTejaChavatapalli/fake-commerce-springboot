package com.sst.FakeCommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sst.FakeCommerce.schemas.Order;

public interface OrderRepository extends JpaRepository<Order,Long> {
    
}
