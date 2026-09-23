package com.sst.FakeCommerce.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.sst.FakeCommerce.dtos.CreateProductRequestDto;
import com.sst.FakeCommerce.dtos.GetProductResponseDto;
import com.sst.FakeCommerce.dtos.GetProductWithDetailsResponseDto;
import com.sst.FakeCommerce.schemas.Product;
import com.sst.FakeCommerce.services.ProductService;

import lombok.RequiredArgsConstructor;

import java.util.List;

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
    public List<GetProductResponseDto> getAllProducts() {
        return this.productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public GetProductResponseDto getProductById(@PathVariable("id") Long id){
        return productService.getProductById(id);
    }

    @GetMapping("/{id}/details")
    public GetProductWithDetailsResponseDto getProductWithDetailsById(@PathVariable("id") Long id){
        return  productService.getProductWithDetailsById(id);
    }
    
 
    @PostMapping
    public Product createProduct(@RequestBody CreateProductRequestDto  requestDto) {
      return productService.creaProduct(requestDto);
    }
    
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") Long id){
        productService.deleteProduct(id);
    }


    @GetMapping("/search")
    public List<Product> getProductsByCategory(@RequestParam ("categoryName") String category) {
            return productService.getProductsByCategory(category);
    }

    @GetMapping("/distinct-categories")
    public List<String> getAllCategories(){
        return  productService.getAllDistinctCategories();
    }
    
}
