package com.sst.FakeCommerce.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sst.FakeCommerce.dtos.CreateCategoryRequestDto;
import com.sst.FakeCommerce.exceptions.ResourceNotFoundException;
import com.sst.FakeCommerce.repositories.CategoryRepository;
import com.sst.FakeCommerce.schemas.Category;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public Category createCategory(CreateCategoryRequestDto categoryRequestDto ){

        Category newCategory = Category.builder()
            .name(categoryRequestDto.getName())
            .build();

        return categoryRepository.save(newCategory);
    }


    public List<Category> getAllCategories(){
        return  categoryRepository.findAll();
    }

    public Category getCategoryById(Long id){
        return categoryRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Category with id " + id+" not found"));
    }

    public void deleteCategoryById(Long id){
        categoryRepository.deleteById(id);
    }
    
}
