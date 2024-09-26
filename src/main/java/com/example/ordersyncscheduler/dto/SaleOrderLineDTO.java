package com.example.ordersyncscheduler.dto;

import com.example.ordersyncscheduler.entity.SaleOrderLine;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaleOrderLineDTO {
    private String part;
    private Integer expectedQty;
    private BigDecimal actualQty;
    private String lotNo;

    public SaleOrderLineDTO of(SaleOrderLine saleOrderLine) {
        return SaleOrderLineDTO.builder()
                .part(saleOrderLine.getMaterial())
                .expectedQty(null)
                .actualQty(saleOrderLine.getQty())
                .lotNo("")
                .build();
    }
}
