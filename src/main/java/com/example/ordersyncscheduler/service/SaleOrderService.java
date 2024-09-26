package com.example.ordersyncscheduler.service;

import com.example.ordersyncscheduler.dto.SaleOrderDTO;
import com.example.ordersyncscheduler.dto.SaleOrderLineDTO;
import com.example.ordersyncscheduler.dto.SaleOrdersDTO;
import com.example.ordersyncscheduler.dto.OrderLineProjection;
import com.example.ordersyncscheduler.repository.SaleOrderRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SaleOrderService {
    private final SaleOrderRepository saleOrderRepository;

    public SaleOrderService(SaleOrderRepository saleOrderRepository) {
        this.saleOrderRepository = saleOrderRepository;
    }

    public List<SaleOrdersDTO> getAllOrdersAndBuildDTOs() {
        List<OrderLineProjection> projections = saleOrderRepository.findAllOrdersWithLines();
        Map<String, SaleOrdersDTO> orderMap = new HashMap<>();

        for (OrderLineProjection projection : projections) {
            SaleOrdersDTO dto = orderMap.computeIfAbsent(projection.getOrderId(), id -> new SaleOrdersDTO(new SaleOrderDTO(0, projection.getOrderRemark(), "料箱入库"), new ArrayList<>()));
            dto.getLines().add(new SaleOrderLineDTO(projection.getLineMaterial(), null, projection.getLineQty(), ""));
        }

        return new ArrayList<>(orderMap.values());
    }
}
