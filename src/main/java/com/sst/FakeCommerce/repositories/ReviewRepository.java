package com.sst.FakeCommerce.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sst.FakeCommerce.schemas.Review;


@Repository 
public interface ReviewRepository extends  JpaRepository<Review,Long>{

    List<Review> findByProductId(@Param("productId") Long productId);

    List<Review> findByOrderId(@Param("orderId")  Long orderId);
}
