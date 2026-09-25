package com.sst.FakeCommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sst.FakeCommerce.schemas.OrderProducts;

@Repository 
public interface OrderProductsRepository extends JpaRepository<OrderProducts, Long> {
}