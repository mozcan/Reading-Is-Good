package com.mozcan.readingIsGood.service;

import com.mozcan.readingIsGood.controller.dto.CustomerCreateRequest;
import com.mozcan.readingIsGood.model.entity.CustomerEntity;

public interface CustomerService {

    CustomerEntity create(CustomerCreateRequest customerCreateRequest);
    CustomerEntity getCustomer(Long id);

    Boolean isUserExist(String email);
}
