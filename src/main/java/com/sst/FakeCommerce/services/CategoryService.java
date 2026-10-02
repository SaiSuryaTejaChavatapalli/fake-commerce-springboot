package com.sst.FakeCommerce.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sst.FakeCommerce.dtos.CreateCategoryRequestDto;
import com.sst.FakeCommerce.exceptions.ResourceNotFoundException;
import com.sst.FakeCommerce.repositories.CategoryRepository;
import com.sst.FakeCommerce.schemas.Category;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j 
@Service 
@RequiredArgsConstructor 
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public Category createCategory(CreateCategoryRequestDto categoryRequestDto ){

        Category newCategory = Category.builder()
            .name(categoryRequestDto.getName())
            .build();
        log.info("Category save calling with {}", categoryRequestDto);
        return categoryRepository.save(newCategory);
    }


    public List<Category> getAllCategories(){
        log.info("getAllCategories called");
        return  categoryRepository.findAll();
    }

    public Category getCategoryById(Long id){
         log.info("getCategoriesById called with id {}", id);
        return categoryRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Category with id " + id+" not found"));
    }

    public void deleteCategoryById(Long id){
         log.info("getCategoryById called with id {}", id);
       Category category=  categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("category with id "+id+" not found"));
        categoryRepository.delete(category);
    }
    
    
}
