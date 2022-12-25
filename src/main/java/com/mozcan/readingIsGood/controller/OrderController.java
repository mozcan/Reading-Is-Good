package com.mozcan.readingIsGood.controller;

import com.mozcan.readingIsGood.controller.dto.*;
import com.mozcan.readingIsGood.exception.BookNotFoundException;
import com.mozcan.readingIsGood.exception.CustomerNotFoundException;
import com.mozcan.readingIsGood.exception.OrderNotFoundException;
import com.mozcan.readingIsGood.service.OrderService;
import com.mozcan.readingIsGood.utils.ModelConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/order")
public class OrderController {

    Logger logger = LoggerFactory.getLogger(OrderController.class);
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createOrder(@RequestBody @Valid OrderCreateRequest orderCreateRequest) {
        try {
            var order = orderService.create(orderCreateRequest);

            var modelConverter = new ModelConverter();
            var response = modelConverter.orderEntityToResponse(order);

            logger.info("Order has been added successfully");

            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (CustomerNotFoundException | BookNotFoundException ex) {
            logger.error(ex.getMessage());

            var errorResponse = new ErrorResponse(ex.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<?> getOrders(@RequestParam Integer page,
                                       @RequestParam Integer limit,
                                       @RequestParam Long customerId) {

        try {
            Pageable paging = PageRequest.of(page, limit);
            var order = orderService.getOrders(customerId,paging);

            return ResponseEntity.ok(order);
        } catch (CustomerNotFoundException ex) {
            logger.error(ex.getMessage());

            var errorResponse = new ErrorResponse(ex.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrder(@PathVariable Long id){
        try {
            var order = orderService.getOrder(id);

            var modelConverter = new ModelConverter();
            var response = modelConverter.orderEntityToResponse(order);

            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (OrderNotFoundException ex) {
            logger.error(ex.getMessage());

            var errorResponse = new ErrorResponse(ex.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
