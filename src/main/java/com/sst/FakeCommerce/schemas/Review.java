package com.sst.FakeCommerce.schemas;

import java.math.BigDecimal;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "reviews")
@SQLDelete (sql = "UPDATE reviews SET deleted_at = CURRENT_TIMESTAMP WHERE id = ?")
@SQLRestriction ("deleted_at IS NULL")
public class Review extends BaseEntity {

    
    private  String title;

    @Column (columnDefinition = "TEXT")
    private String description;

    @Column (nullable = false)
    private  BigDecimal rating;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable=false)
    private Product product;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable=false)
    private Order order;
}
