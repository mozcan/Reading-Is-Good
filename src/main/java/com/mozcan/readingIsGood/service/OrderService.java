package com.mozcan.readingIsGood.service;

import com.mozcan.readingIsGood.controller.dto.OrderCreateRequest;
import com.mozcan.readingIsGood.model.entity.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {
    OrderEntity create(OrderCreateRequest createRequest);
    Page<OrderEntity> getOrders(Long customerId, Pageable pageable);
    OrderEntity getOrder(Long id);

    OrderEntity getCustomerMontlyOrder(Long customerId);
}
