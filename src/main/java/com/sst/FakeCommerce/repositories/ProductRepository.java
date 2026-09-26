package com.sst.FakeCommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sst.FakeCommerce.schemas.Product;
import java.util.List;


@Repository 
public interface ProductRepository extends JpaRepository<Product, Long>{


        // Same functionality with JPQL query
    //  @Query("SELECT p FROM Product p JOIN p.category c WHERE c.name = :categoryName")
    // List<Product> findProductsByCategoryName(@Param("categoryName") String categoryName);
    
     List<Product> findByCategory_Name( @Param("categoryName") String categoryName);


    // @Query (nativeQuery = true,
    //      value = "SELECT p.*, c.name as category  FROM products p INNER JOIN categories c on p.category_id = c.id WHERE p.id = :id")
    
    // Hibernate Query
    @Query ("SELECT p from Product p JOIN FETCH p.category WHERE p.id = :id")
    List<Product> findProductWithDetailsById(@Param ("id") Long id);
    
}
