package com.mozcan.readingIsGood.controller;

import com.mozcan.readingIsGood.controller.dto.ErrorResponse;
import com.mozcan.readingIsGood.exception.CustomerNotFoundException;
import com.mozcan.readingIsGood.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/statics")
public class StatisticsController {
    Logger logger = LoggerFactory.getLogger(StatisticsController.class);

    private final OrderService orderService;

    public StatisticsController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<?> getCustomerMontlyOrder(@PathVariable Long customerId) {
        try {
            var order = orderService.getCustomerMontlyOrder(customerId);
            return null;
        } catch (CustomerNotFoundException ex) {
            logger.error(ex.getMessage());

            var errorResponse = new ErrorResponse(ex.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
