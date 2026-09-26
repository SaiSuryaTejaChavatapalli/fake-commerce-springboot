package com.sst.FakeCommerce.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sst.FakeCommerce.dtos.CreateReviewRequestDto;
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
import org.springframework.web.bind.annotation.GetMapping;




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
    public ResponseEntity<ApiResponse<List<Review>>> getAllReviews(){
      List<Review> reviews= reviewService.getAllReviews();
      return ResponseEntity.ok(ApiResponse.success(reviews, "Reviews fetched successfully"));
    }

    


}
