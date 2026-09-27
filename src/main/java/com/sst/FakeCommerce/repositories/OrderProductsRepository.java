package com.sst.FakeCommerce.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sst.FakeCommerce.schemas.Order;
import com.sst.FakeCommerce.schemas.OrderProducts;

@Repository 
public interface OrderProductsRepository extends JpaRepository<OrderProducts, Long> {

    List<OrderProducts> findByOrderId(Long orderId);

    @Query("SELECT op FROM OrderProducts op JOIN FETCH op.product WHERE op.order = :order")
    List<OrderProducts> findByOrderWithProduct(@Param ("order") Order order);
    
}