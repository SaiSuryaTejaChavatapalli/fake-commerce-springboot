package com.sst.FakeCommerce.dtos;

import java.time.LocalDateTime;
import java.util.List;

import com.sst.FakeCommerce.enums.OrderStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@AllArgsConstructor 
@NoArgsConstructor 
@Builder 
public class GetOrderResponseDto {

    private Long id; 

    private OrderStatus status;

    private  List<OrderItemResponseDto> items;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
