package com.sst.FakeCommerce.dtos;

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
public class UpdateOrderRequestDto {

    private OrderStatus status;

    private List<OrderItemActionDto> orderItems;
    
}
