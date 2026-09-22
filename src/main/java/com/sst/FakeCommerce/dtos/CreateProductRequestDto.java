package com.sst.FakeCommerce.dtos;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data 
@AllArgsConstructor 
@NoArgsConstructor 
public class CreateProductRequestDto {
    
    private String title;
   
    private String description;

    private BigDecimal price;

    private String image;

    private  String category;

    private  String rating;
}
