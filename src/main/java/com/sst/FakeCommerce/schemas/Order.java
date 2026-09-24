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


    // This will create a join table, but we can't have any extra attributes/columns in it
    // @ManyToMany 
    // @JoinTable(
    //     name = "order_products",
    //     joinColumns = @JoinColumn (name="order_id"), // The FK belonging to the same schema - Order
    //     inverseJoinColumns = @JoinColumn (name = "product_id") // The FK belonging to the other schema - Product

    // )
    // private List<Product> products;
}
