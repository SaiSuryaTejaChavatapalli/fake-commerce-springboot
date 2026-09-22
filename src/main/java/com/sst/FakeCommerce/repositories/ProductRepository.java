package com.sst.FakeCommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sst.FakeCommerce.schemas.Product;
import java.util.List;


@Repository 
public interface ProductRepository extends JpaRepository<Product, Long>{


    List<Product> findByCategory(String category);


    @Query (nativeQuery = true, value = "SELECT DISTINCT category from products")
    List<String> findAllDistinctCategories();

    
}
