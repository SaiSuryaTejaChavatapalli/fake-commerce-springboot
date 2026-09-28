package com.sst.FakeCommerce.services;

import com.sst.FakeCommerce.adapters.ReviewAdapter;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sst.FakeCommerce.dtos.CreateReviewRequestDto;
import com.sst.FakeCommerce.dtos.GetReviewResponseDto;
import com.sst.FakeCommerce.exceptions.ResourceNotFoundException;
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

    private final ReviewAdapter reviewAdapter;
    
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

    public List<GetReviewResponseDto> getAllReviews() {
        return reviewAdapter.mapToGetReviewResponseDtoList(reviewRepository.findAll());   
    }

    public GetReviewResponseDto getReviewById(Long id){
        return reviewRepository.findById(id)
                .map(reviewAdapter::mapToGetReviewResponseDto)
                .orElseThrow(()-> new ResourceNotFoundException("Review not found with ID: "+ id));

    }

    public  List<GetReviewResponseDto> getReviewsByProductId(Long productId){
        return reviewAdapter.mapToGetReviewResponseDtoList(reviewRepository.findByProductId(productId));
    }

    public  List<GetReviewResponseDto> getReviewsByOrderId(Long orderId){
        return  reviewAdapter.mapToGetReviewResponseDtoList(reviewRepository.findByOrderId(orderId));
    }


    public void deleteReview(Long id){
        Review review= reviewRepository.findById(id)
                        .orElseThrow(()-> new ResourceNotFoundException("Review not found with ID:"+ id));
        reviewRepository.delete(review);
    }

}
