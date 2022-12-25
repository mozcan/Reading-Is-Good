package com.mozcan.readingIsGood.dao;

import com.mozcan.readingIsGood.model.entity.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderJpaRepository extends JpaRepository<OrderEntity, Long> {

    @Query(value = "SELECT * FROM ORDER  where CUSTOMER_ID=?1",
            countQuery = "SELECT count(*) FROM ORDER  where CUSTOMER_ID=?1",
            nativeQuery = true)
    Page<OrderEntity> getCustomerOrders(Long customerId, Pageable pageable);

    @Query("SELECT c.orderCreatedTime,COUNT(c.orderCreatedTime) FROM OrderEntity c WHERE c.customer.id=?1 GROUP BY c.orderCreatedTime")
    List<Object[]> getCustomerMontlyOrder(Long customerId);
}
