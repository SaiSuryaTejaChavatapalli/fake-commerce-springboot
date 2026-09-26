package com.sst.FakeCommerce.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sst.FakeCommerce.dtos.CreateOrderRequestDto;
import com.sst.FakeCommerce.schemas.Order;
import com.sst.FakeCommerce.services.OrderService;
import com.sst.FakeCommerce.utils.ApiResponse;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController 
@RequestMapping ("/api/v1/orders")
@RequiredArgsConstructor 
public class OrderController {

    private final OrderService orderService;
    
    @PostMapping
    public ResponseEntity<ApiResponse<Order>>  createOrder(@RequestBody CreateOrderRequestDto orderRequestDto) {
        Order order= orderService.createOrder(orderRequestDto);
        return  ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(order,"Order created successfully"));
    }
    
}
