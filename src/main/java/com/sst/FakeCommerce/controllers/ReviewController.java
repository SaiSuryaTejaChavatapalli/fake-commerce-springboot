package com.sst.FakeCommerce.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sst.FakeCommerce.dtos.CreateReviewRequestDto;
import com.sst.FakeCommerce.dtos.GetReviewResponseDto;
import com.sst.FakeCommerce.schemas.Review;
import com.sst.FakeCommerce.services.ReviewService;
import com.sst.FakeCommerce.utils.ApiResponse;

import lombok.Builder;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;




@Builder 
@RequiredArgsConstructor 
@RestController
@RequestMapping ("/api/v1/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<ApiResponse<Review>>  createReview(@RequestBody CreateReviewRequestDto reviewRequestDto){

      Review review= reviewService.createReview(reviewRequestDto);
      return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(review, " Review created successfully"));
    }


    @GetMapping
    public ResponseEntity<ApiResponse<List<GetReviewResponseDto>>> getAllReviews(){
      List<GetReviewResponseDto> reviews= reviewService.getAllReviews();
      return ResponseEntity.ok(ApiResponse.success(reviews, "Reviews fetched successfully"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteReview(@PathVariable("id") Long id) {
        reviewService.deleteReview(id);
        return ResponseEntity.ok(ApiResponse.success(null, "Review deleted successfully with ID: "+id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<GetReviewResponseDto>> getReviewById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(ApiResponse.success(reviewService.getReviewById(id), "Review fetched successfully with ID: "+id));
         
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<ApiResponse <List<GetReviewResponseDto>>> getReviewsByProductId(@PathVariable("productId") Long productId) {
        return ResponseEntity.ok(ApiResponse.success(reviewService.getReviewsByProductId(productId), "Review fetched successfully for Product ID: "+productId));
        
        
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<ApiResponse< List<GetReviewResponseDto>>> getReviewsByOrderId(@PathVariable("orderId") Long orderId) {

        return ResponseEntity.ok(ApiResponse.success(reviewService.getReviewsByOrderId(orderId), "Review fetched successfully for Order ID: "+orderId));
        
    }


}
