package com.sst.FakeCommerce.schemas;

import java.util.List;

import com.sst.FakeCommerce.enums.OrderStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@AllArgsConstructor 
@NoArgsConstructor 
@Builder 
@Entity 
@Table (name = "orders")
public class Order extends BaseEntity {

    private OrderStatus status;


    @ManyToMany 
    @JoinTable(
        name = "order_products",
        joinColumns = @JoinColumn (name="order_id"),
        inverseJoinColumns = @JoinColumn (name = "product_id")

    )
    private List<Product> products;
}
