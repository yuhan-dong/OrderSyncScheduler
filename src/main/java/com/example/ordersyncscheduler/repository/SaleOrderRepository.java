package com.example.ordersyncscheduler.repository;

import com.example.ordersyncscheduler.dto.OrderLineProjection;
import com.example.ordersyncscheduler.entity.SaleOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SaleOrderRepository extends JpaRepository<SaleOrder, String> {

    @Query("SELECT OrderLineProjection(so.id, so.remark, sol.material, sol.qty) " +
            "FROM SaleOrder so LEFT JOIN SaleOrderLine sol ON so.id = sol.orderId")
    List<OrderLineProjection> findAllOrdersWithLines();

}
