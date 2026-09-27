package com.sst.FakeCommerce.dtos;

import com.sst.FakeCommerce.enums.OrderItemAction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data 
@AllArgsConstructor 
@NoArgsConstructor 
@Builder 
public class OrderItemActionDto {

    private Long productId;

    private Integer quantity;

    private  OrderItemAction action;
}
