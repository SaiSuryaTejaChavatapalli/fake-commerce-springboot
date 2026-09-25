package com.sst.FakeCommerce.services;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sst.FakeCommerce.dtos.CreateReviewRequestDto;
import com.sst.FakeCommerce.repositories.OrderRepository;
import com.sst.FakeCommerce.repositories.ProductRepository;
import com.sst.FakeCommerce.repositories.ReviewRepository;
import com.sst.FakeCommerce.schemas.Order;
import com.sst.FakeCommerce.schemas.Product;
import com.sst.FakeCommerce.schemas.Review;

import lombok.RequiredArgsConstructor;


@Service 
@RequiredArgsConstructor 
public class ReviewService {

    private final ReviewRepository reviewRepository;

    private final ProductRepository productRepository;

    private final OrderRepository orderRepository;
    
    public Review createReview(CreateReviewRequestDto reviewRequestDto){

        Product product =productRepository.findById(reviewRequestDto.getProductId()).orElseThrow(() -> new RuntimeException("Product not found"));

        Order order =orderRepository.findById(reviewRequestDto.getOrderId()).orElseThrow(() -> new RuntimeException("Order not found"));

           Review review= Review.builder()
            .title(reviewRequestDto.getTitle())
            .description(reviewRequestDto.getDescription())
            .rating(reviewRequestDto.getRating())
            .product(product)
            .order(order)
            .build();
        
            return reviewRepository.save(review);
    }

    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }
}
