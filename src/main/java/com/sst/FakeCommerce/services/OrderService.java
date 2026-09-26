package com.sst.FakeCommerce.services;

import com.sst.FakeCommerce.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import com.sst.FakeCommerce.dtos.CreateOrderRequestDto;
import com.sst.FakeCommerce.enums.OrderStatus;
import com.sst.FakeCommerce.exceptions.ResourceNotFoundException;
import com.sst.FakeCommerce.repositories.OrderProductsRepository;
import com.sst.FakeCommerce.repositories.OrderRepository;
import com.sst.FakeCommerce.schemas.Order;
import com.sst.FakeCommerce.schemas.OrderProducts;
import com.sst.FakeCommerce.schemas.Product;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class OrderService {

    private final ProductRepository productRepository;

    private final OrderRepository orderRepository;

    private final OrderProductsRepository orderProductsRepository;

    

    @Transactional 
    public Order  createOrder(CreateOrderRequestDto createOrderRequestDto){

        Order order = Order.builder().status(OrderStatus.PENDING).build();
        orderRepository.save(order);

        createOrderRequestDto.getProducts()
        .stream()
        .map(item -> {
            Product product= productRepository
                .findById(item.getProductId())
                .orElseThrow(()-> new ResourceNotFoundException("Product not found"));

            return  OrderProducts.builder()
            .order(order)
            .product(product)
            .quantity(item.getQuantity())
            .build();

        }).forEach(orderProductsRepository:: save);
        
        return  order;
        
    }
}
