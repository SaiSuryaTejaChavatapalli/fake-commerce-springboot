package com.sst.FakeCommerce.adapters;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.sst.FakeCommerce.dtos.GetReviewResponseDto;
import com.sst.FakeCommerce.schemas.Review;

@Component 
public class ReviewAdapter {


    public List<GetReviewResponseDto> mapToGetReviewResponseDtoList(List<Review> reviews){
        return reviews.stream()
                .map(this:: mapToGetReviewResponseDto)
                .collect(Collectors.toList());
    }
    

    public GetReviewResponseDto mapToGetReviewResponseDto(Review review){
        return GetReviewResponseDto.builder()
                .id(review.getId())
                .productId(review.getProduct().getId())
                .orderId(review.getOrder().getId())
                .rating(review.getRating())
                .title(review.getTitle())
                .description(review.getDescription())
                .createdAt(review.getCreatedAt())
                .build();
    }
}
