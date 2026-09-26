package com.sst.FakeCommerce.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.sst.FakeCommerce.dtos.CreateProductRequestDto;
import com.sst.FakeCommerce.dtos.GetProductResponseDto;
import com.sst.FakeCommerce.dtos.GetProductWithDetailsResponseDto;
import com.sst.FakeCommerce.schemas.Product;
import com.sst.FakeCommerce.services.ProductService;
import com.sst.FakeCommerce.utils.ApiResponse;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor 
public class ProductController {

    private final ProductService productService;

    
    @GetMapping
    public ResponseEntity<ApiResponse<List<GetProductResponseDto>>>  getAllProducts() {
        return ResponseEntity.ok(ApiResponse.success(this.productService.getAllProducts(), "Products fetched successfully"));
    };

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<GetProductResponseDto>> getProductById(@PathVariable("id") Long id){
        return ResponseEntity.ok(ApiResponse.success(productService.getProductById(id), "Product with id "+id+" fetched successfully"));
        
    }

    @GetMapping("/{id}/details")
    public ResponseEntity<ApiResponse<GetProductWithDetailsResponseDto>> getProductWithDetailsById(@PathVariable("id") Long id){
        return ResponseEntity.ok(ApiResponse.success(productService.getProductWithDetailsById(id), "Product details with id "+id+" fetched successfully"));
        
    }
    
 
    @PostMapping
    public ResponseEntity<ApiResponse<Product>>  createProduct(@RequestBody CreateProductRequestDto  requestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(productService.creaProduct(requestDto), "Product created successfully"));
      
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteProduct(@PathVariable("id") Long id){
        productService.deleteProduct(id);
        return  ResponseEntity.ok(ApiResponse.success(null, "Product with id "+ id+" deleted successfully"));
        
    }


    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<Product>>>  getProductsByCategory(@RequestParam ("categoryName") String category) {
        return ResponseEntity.ok(ApiResponse.success(productService.getProductsByCategory(category), "Products with category name '"+category+"' fetched successfully") );
    }  
    
}
