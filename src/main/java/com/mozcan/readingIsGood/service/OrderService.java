package com.mozcan.readingIsGood.service;

import com.mozcan.readingIsGood.controller.dto.OrderCreateRequest;
import com.mozcan.readingIsGood.controller.dto.StaticsCustomerMontlyOrderResponse;
import com.mozcan.readingIsGood.model.entity.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderService {
    OrderEntity create(OrderCreateRequest createRequest);
    Page<OrderEntity> getOrders(Long customerId, Pageable pageable);
    OrderEntity getOrder(Long id);

    List<StaticsCustomerMontlyOrderResponse> getCustomerMontlyOrder(Long customerId);
}
