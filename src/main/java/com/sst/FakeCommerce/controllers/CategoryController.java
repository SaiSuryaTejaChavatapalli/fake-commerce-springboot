package com.sst.FakeCommerce.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sst.FakeCommerce.dtos.CreateCategoryRequestDto;
import com.sst.FakeCommerce.schemas.Category;
import com.sst.FakeCommerce.services.CategoryService;
import com.sst.FakeCommerce.utils.ApiResponse;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ApiResponse<Category>> creaCategory(@RequestBody CreateCategoryRequestDto createCategoryRequestDto) { 
       Category category= categoryService.createCategory(createCategoryRequestDto);
       return  ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success(category, "Category created successfully"));
    }

    @GetMapping
    public  ResponseEntity<ApiResponse<List<Category>>> getAllCategories(){
       List<Category> categories= categoryService.getAllCategories();
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(categories, "Categories fetched successfully"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Category>> getCategoryById(@PathVariable("id") Long id) {
        Category category = categoryService.getCategoryById(id);
        return ResponseEntity.ok(ApiResponse.success(category, "Categories fetched successfully"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteCategoryById(@PathVariable("id") Long id){
        categoryService.deleteCategoryById(id);
        return  ResponseEntity.ok(ApiResponse.success(null,"Category deleted successfully"));
        
    }
    

    
}
