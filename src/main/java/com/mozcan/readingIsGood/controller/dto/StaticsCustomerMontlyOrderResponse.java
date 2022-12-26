package com.mozcan.readingIsGood.controller.dto;

import java.time.LocalDate;

public interface StaticsCustomerMontlyOrderResponse {

    Integer getTotalBook();

    Double getTotalPrice();
    Integer getTotalOrder();
    LocalDate getOrderCreateDate();
}
