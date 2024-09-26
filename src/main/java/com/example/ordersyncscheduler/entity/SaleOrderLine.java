package com.example.ordersyncscheduler.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "saleorderline")
public class SaleOrderLine {
    @Id
    private String id;

    private String orderId;
    private Integer lineNo;
    private String material;
    private BigDecimal qty;
}
