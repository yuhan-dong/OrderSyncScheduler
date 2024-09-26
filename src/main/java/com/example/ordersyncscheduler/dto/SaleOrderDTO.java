package com.example.ordersyncscheduler.dto;

import com.example.ordersyncscheduler.entity.SaleOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaleOrderDTO {
    private int state;
    private String remark;
    private String template;

    public SaleOrderDTO of(SaleOrder saleOrder) {
        return SaleOrderDTO.builder()
                .state(0)
                .remark(saleOrder.getRemark())
                .template("料箱入库")
                .build();
    }
}
