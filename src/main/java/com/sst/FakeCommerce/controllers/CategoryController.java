package com.sst.FakeCommerce.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sst.FakeCommerce.dtos.CreateCategoryRequestDto;
import com.sst.FakeCommerce.schemas.Category;
import com.sst.FakeCommerce.services.CategoryService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




@RestController 
@RequestMapping ("/api/v1/categories")
@RequiredArgsConstructor 
public class CategoryController {

    private final CategoryService categoryService;
    

    @PostMapping
    public Category postMethodName(@RequestBody CreateCategoryRequestDto createCategoryRequestDto) { 
        return categoryService.createCategory(createCategoryRequestDto);
    }

    @GetMapping
    public  List<Category> getAllCategories(){
        return categoryService.getAllCategories();
    }

    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable("id") Long id) {
        return categoryService.getCategoryById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteCategoryById(@PathVariable("id") Long id){
        categoryService.deleteCategoryById(id);
    }
    
}
