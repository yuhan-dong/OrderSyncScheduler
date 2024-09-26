package com.example.ordersyncscheduler.service;

import com.example.ordersyncscheduler.dto.SaleOrdersDTO;
import com.example.ordersyncscheduler.util.RestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduledOrderService {

    private final SaleOrderService saleOrderService;
    private final RestClient restClient;

    @Autowired
    public ScheduledOrderService(SaleOrderService saleOrderService, RestClient restClient) {
        this.saleOrderService = saleOrderService;
        this.restClient = restClient;
    }

    @Scheduled(cron = "${sync.orders.cron}")
    public void scheduleTask() {
        try {
            restClient.signIn();
            List<SaleOrdersDTO> orders = saleOrderService.getAllOrdersAndBuildDTOs();
            for (SaleOrdersDTO order : orders) {
                restClient.sendOrder(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
