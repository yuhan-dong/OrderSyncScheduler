package com.example.ordersyncscheduler.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderLineProjection {
    private String orderId;
    private String orderRemark;
    private String lineMaterial;
    private BigDecimal lineQty;
}
