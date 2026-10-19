package com.sst.FakeCommerce.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.sst.FakeCommerce.dtos.CreateProductRequestDto;
import com.sst.FakeCommerce.dtos.GetProductResponseDto;
import com.sst.FakeCommerce.dtos.GetProductWithDetailsResponseDto;
import com.sst.FakeCommerce.repositories.ProductRepository;
import com.sst.FakeCommerce.schemas.Category;
import com.sst.FakeCommerce.schemas.Product;
import com.sst.FakeCommerce.services.cache.ProductRedisCache;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j 
@Service 
@RequiredArgsConstructor 
public class ProductService {
    private final ProductRepository productRepository;

    private final CategoryService categoryService;

    private final ProductRedisCache productRedisCache;


    public List<GetProductResponseDto> getAllProducts(){
        List<Product> products= productRepository.findAll();
         log.info("getAllProducts called");
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

        Optional<GetProductResponseDto> cachedSummary = productRedisCache.getSummary(id);
        if(cachedSummary.isPresent()){
            return cachedSummary.get();
        }


        log.info("getProductById called with {}", id);
        GetProductResponseDto response = productRepository.findById(id).map(product -> GetProductResponseDto.builder()
                .id(product.getId())
                .title(product.getTitle())
                .description(product.getDescription())
                .image(product.getImage())
                .rating(product.getRating())
                .build() )
                .orElseThrow(()-> new RuntimeException("Product not found"));

        productRedisCache.putSummary(id, response);

        return response;
    }


    public Product creaProduct(CreateProductRequestDto requestDto){
        log.info("Create Product called with {}", requestDto);
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
        log.info("Create Product called with{}", id);
        productRepository.deleteById(id);
    }


    public List<Product> getProductsByCategory(String categoryName){
        log.info("Create Product by category called with {}", categoryName);
       return productRepository.findByCategory_Name(categoryName);
    }


  

    public GetProductWithDetailsResponseDto getProductWithDetailsById(Long id) {
        log.info("Get Product with details called with{}", id);
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
