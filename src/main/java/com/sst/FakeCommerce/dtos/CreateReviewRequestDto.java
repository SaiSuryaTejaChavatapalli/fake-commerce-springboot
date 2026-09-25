package com.sst.FakeCommerce.dtos;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data 
@Builder 
@AllArgsConstructor 
@NoArgsConstructor 
public class CreateReviewRequestDto {

    private String title;

    private  String description;

    private  BigDecimal rating;

    private Long productId;

    private Long orderId;
}
