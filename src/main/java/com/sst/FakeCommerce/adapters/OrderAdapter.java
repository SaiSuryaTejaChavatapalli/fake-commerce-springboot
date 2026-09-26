package com.sst.FakeCommerce.adapters;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.sst.FakeCommerce.dtos.GetOrderResponseDto;
import com.sst.FakeCommerce.dtos.OrderItemResponseDto;
import com.sst.FakeCommerce.repositories.OrderProductsRepository;
import com.sst.FakeCommerce.schemas.Order;
import com.sst.FakeCommerce.schemas.OrderProducts;

import lombok.RequiredArgsConstructor;



@Component 
@RequiredArgsConstructor 
public class OrderAdapter {
    
    private  final OrderProductsRepository orderProductsRepository;


    public List<GetOrderResponseDto> mapToGetOrderResponseDtoList(List<Order> orders){
        return orders.stream()
        .map(this::mapToGetOrderResponseDto)
        .collect(Collectors.toList());
    }


    public  GetOrderResponseDto mapToGetOrderResponseDto(Order order){
        List<OrderProducts> orderProducts = orderProductsRepository.findByOrderId(order.getId());
        List<OrderItemResponseDto> items = mapToOrderItemResponseDto(orderProducts);
        return GetOrderResponseDto
                    .builder()
                    .id(order.getId())
                    .status(order.getStatus())
                    .createdAt(order.getCreatedAt())
                    .updatedAt(order.getUpdatedAt())
                    .items(items)
                    .build();
    }


    public List<OrderItemResponseDto> mapToOrderItemResponseDto(List<OrderProducts> orderProducts){

        return orderProducts.stream()
            .map(orderProduct -> OrderItemResponseDto.builder()
            .productId(orderProduct.getProduct().getId())
            .productName(orderProduct.getProduct().getTitle())
            .productPrice(orderProduct.getProduct().getPrice())
            .productImage(orderProduct.getProduct().getImage())
            .quantity(orderProduct.getQuantity())
            .subTotal(orderProduct.getProduct().getPrice().multiply(BigDecimal.valueOf(orderProduct.getQuantity())))
            .build()) 
        .collect(Collectors.toList());

    }

}


 
            
            
             
           