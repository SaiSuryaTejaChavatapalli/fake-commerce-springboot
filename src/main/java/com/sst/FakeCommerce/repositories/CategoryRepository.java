package com.sst.FakeCommerce.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sst.FakeCommerce.schemas.Category;

@Repository 
public interface CategoryRepository extends  JpaRepository<Category, Long>{

} 