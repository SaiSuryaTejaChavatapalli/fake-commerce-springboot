package com.sst.FakeCommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sst.FakeCommerce.schemas.OrderProducts;

public interface OrderProductsRepository extends JpaRepository<OrderProducts, Long> {
}