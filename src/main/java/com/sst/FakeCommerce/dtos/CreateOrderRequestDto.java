package com.sst.FakeCommerce.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@AllArgsConstructor 
@NoArgsConstructor 
public class CreateOrderRequestDto {
    
     private List<OrderItemRequestDto> products;

    // Example
    //      {
    //   "products": [
    //     {
    //       "productId": 10,
    //       "quantity": 2
    //     },
    //     {
    //       "productId": 25,
    //       "quantity": 1
    //     }
    //   ]
    // }
}
