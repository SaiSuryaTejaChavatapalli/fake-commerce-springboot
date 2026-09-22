package com.sst.FakeCommerce.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sst.FakeCommerce.dtos.CreateProductRequestDto;
import com.sst.FakeCommerce.repositories.ProductRepository;
import com.sst.FakeCommerce.schemas.Product;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public Product getProductById(Long id){
        return  productRepository.findById(id)
        .orElseThrow(()-> new RuntimeException("Product not found"));
    }


    public Product creaProduct(CreateProductRequestDto requestDto){

        Product newProduct = Product.builder()
        .title(requestDto.getTitle())
        .description(requestDto.getDescription())
        .image(requestDto.getImage())
        .price(requestDto.getPrice())
        .category(requestDto.getCategory())
        .rating(requestDto.getRating())
        .build();
        
       return productRepository.save(newProduct);
    }


    public  void deleteProduct(Long id){
        productRepository.deleteById(id);
    }


    public List<Product> getProductsByCategory(String category){
       return productRepository.findByCategory(category);
    }


    public List<String> getAllDistinctCategories(){
        return  productRepository.findAllDistinctCategories();
    }


}
