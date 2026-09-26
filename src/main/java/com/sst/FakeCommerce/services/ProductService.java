package com.sst.FakeCommerce.services;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.sst.FakeCommerce.dtos.CreateProductRequestDto;
import com.sst.FakeCommerce.dtos.GetProductResponseDto;
import com.sst.FakeCommerce.dtos.GetProductWithDetailsResponseDto;
import com.sst.FakeCommerce.repositories.ProductRepository;
import com.sst.FakeCommerce.schemas.Category;
import com.sst.FakeCommerce.schemas.Product;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class ProductService {
    private final ProductRepository productRepository;

    private final CategoryService categoryService;

    public List<GetProductResponseDto> getAllProducts(){
        List<Product> products= productRepository.findAll();
        
       return products.stream().map(product -> GetProductResponseDto.builder()
                .id(product.getId())
                .title(product.getTitle())
                .description(product.getDescription())
                .image(product.getImage())
                .rating(product.getRating())
                .build())
                .collect(Collectors.toList());
    }

    public GetProductResponseDto getProductById(Long id){
        return productRepository.findById(id).map(product -> GetProductResponseDto.builder()
                .id(product.getId())
                .title(product.getTitle())
                .description(product.getDescription())
                .image(product.getImage())
                .rating(product.getRating())
                .build() )
                .orElseThrow(()-> new RuntimeException("Product not found"));
    }


    public Product creaProduct(CreateProductRequestDto requestDto){

        Category category = categoryService.getCategoryById(requestDto.getCategoryId());

        Product newProduct = Product.builder()
        .title(requestDto.getTitle())
        .description(requestDto.getDescription())
        .image(requestDto.getImage())
        .price(requestDto.getPrice())
        .category(category) 
        .rating(requestDto.getRating())
        .build();
        
       return productRepository.save(newProduct);
    }


    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }


    public List<Product> getProductsByCategory(String categoryName){
       return productRepository.findByCategory_Name(categoryName);
    }


  

    public GetProductWithDetailsResponseDto getProductWithDetailsById(Long id) {

        Product product = productRepository.findProductWithDetailsById(id).get(0);


        return GetProductWithDetailsResponseDto
            .builder()
            .id(product.getId())
            .title(product.getTitle())
            .description(product.getDescription())
            .price(product.getPrice())
            .category(product.getCategory().getName())
            .image(product.getImage())
            .rating(product.getRating())
            .build(); 
    }


}
