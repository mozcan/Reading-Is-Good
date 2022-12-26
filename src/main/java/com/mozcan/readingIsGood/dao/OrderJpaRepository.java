package com.mozcan.readingIsGood.dao;

import com.mozcan.readingIsGood.controller.dto.StaticsCustomerMontlyOrderResponse;
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

    @Query(value = "SELECT COUNT(ORDER_BOOK.BOOK_ID) as totalBook,SUM(BOOK.PRICE) as totalPrice,COUNT(ORDER.ID) as totalOrder " +
            ",ORDER_CREATED_TIME as orderCreateDate "
  + "FROM CUSTOMER  , BOOK  , " +
            "ORDER  , ORDER_BOOK  WHERE CUSTOMER.ID= 1 AND CUSTOMER.ID= ORDER.CUSTOMER_ID AND " +
            "ORDER.ID = ORDER_BOOK.ORDER_ID AND ORDER_BOOK.BOOK_ID = BOOK.ID GROUP BY ORDER.ORDER_CREATED_TIME",nativeQuery = true)
        List<StaticsCustomerMontlyOrderResponse> getCustomerMontlyOrder(Long customerId);

    /*
    SELECT COUNT(BOOK.ID),SUM(BOOK.PRICE),COUNT(ORDER_BOOK.BOOK_ID) ,ORDER_CREATED_TIME  FROM CUSTOMER  , BOOK  ,
    ORDER  , ORDER_BOOK  WHERE CUSTOMER.ID= 1 AND CUSTOMER.ID= ORDER.CUSTOMER_ID AND
    ORDER.ID = ORDER_BOOK.ORDER_ID AND ORDER_BOOK.BOOK_ID = BOOK.ID GROUP BY ORDER.ORDER_CREATED_TIME
     */
}
