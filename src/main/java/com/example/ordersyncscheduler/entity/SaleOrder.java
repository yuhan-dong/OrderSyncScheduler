package com.example.ordersyncscheduler.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "saleorder")
public class SaleOrder {
    @Id
    private String id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;

    private Integer lineNum;
    private String remark;

}
