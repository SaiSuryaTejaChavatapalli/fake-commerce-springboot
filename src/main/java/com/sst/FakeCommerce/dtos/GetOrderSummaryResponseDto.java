package com.sst.FakeCommerce.dtos;

import java.math.BigDecimal;
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
public class GetOrderSummaryResponseDto {

    private Long id;

    private OrderStatus status;

    private List<OrderItemResponseDto> items;

    private Integer totalItems;

    private BigDecimal totalPrice;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
    
}
