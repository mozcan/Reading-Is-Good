package com.mozcan.readingIsGood.controller;


import com.mozcan.readingIsGood.controller.dto.CustomerCreateRequest;
import com.mozcan.readingIsGood.controller.dto.CustomerResponse;
import com.mozcan.readingIsGood.controller.dto.ErrorResponse;
import com.mozcan.readingIsGood.exception.CustomerExistException;
import com.mozcan.readingIsGood.service.CustomerService;
import com.mozcan.readingIsGood.utils.ModelConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/customer")
public class CustomerController {

    Logger logger = LoggerFactory.getLogger(CustomerController.class);

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createCustomer(@RequestBody @Valid CustomerCreateRequest customerCreateRequest) {
        try {
            var customer = customerService.create(customerCreateRequest);

            var modelConverter = new ModelConverter();
            var response = modelConverter.customerEntityToCustomerResponse(customer);

            logger.info("Customer has been added successfully");

            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (CustomerExistException ex) {
            logger.error(ex.getMessage());

            var errorResponse = new ErrorResponse(ex.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
