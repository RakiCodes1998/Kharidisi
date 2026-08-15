package com.kharidisi.kharidisibackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "total_amount", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalAmount;

    @Column(name = "order_status", nullable = false, length = 100)
    private String orderStatus;

    @Column(name = "order_date")
    private LocalDateTime orderDate;

    @PrePersist
    public void onCreate() {
        this.orderDate = LocalDateTime.now();

        if (this.orderStatus == null) {
            this.orderStatus = "placed";
        }
    }
}

