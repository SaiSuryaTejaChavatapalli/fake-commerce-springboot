package com.sst.FakeCommerce.services;

import com.sst.FakeCommerce.adapters.ReviewAdapter;
import java.util.List;
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
import lombok.extern.slf4j.Slf4j;


@Slf4j 
@Service 
@RequiredArgsConstructor 
public class ReviewService {

    

    private final ReviewRepository reviewRepository;

    private final ProductRepository productRepository;

    private final OrderRepository orderRepository;

    private final ReviewAdapter reviewAdapter;
    
    public Review createReview(CreateReviewRequestDto reviewRequestDto){

        Product product =productRepository.findById(reviewRequestDto.getProductId()).orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: "+ reviewRequestDto.getProductId()));

        Order order =orderRepository.findById(reviewRequestDto.getOrderId()).orElseThrow(() -> new ResourceNotFoundException("Order not found with ID: "+ reviewRequestDto.getOrderId()));

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
        log.info("Get ALl Reviews called");
        return reviewAdapter.mapToGetReviewResponseDtoList(reviewRepository.findAll());   
    }

    public GetReviewResponseDto getReviewById(Long id){
        log.info("Get Review By ID {} called", id);
        return reviewRepository.findById(id)
                .map(reviewAdapter::mapToGetReviewResponseDto)
                .orElseThrow(()-> new ResourceNotFoundException("Review not found with ID: "+ id));

    }

    public  List<GetReviewResponseDto> getReviewsByProductId(Long productId){
        log.info("Get Reviews By product ID {} called", productId);
        return reviewAdapter.mapToGetReviewResponseDtoList(reviewRepository.findByProductId(productId));
    }

    public  List<GetReviewResponseDto> getReviewsByOrderId(Long orderId){
        log.info("Get Reviews By order ID {} called", orderId);
        return  reviewAdapter.mapToGetReviewResponseDtoList(reviewRepository.findByOrderId(orderId));
    }


    public void deleteReview(Long id){
        log.info("Delete Review By  ID {} called", id);
        Review review= reviewRepository.findById(id)
                        .orElseThrow(()-> new ResourceNotFoundException("Review not found with ID:"+ id));
        reviewRepository.delete(review);
    }

}
