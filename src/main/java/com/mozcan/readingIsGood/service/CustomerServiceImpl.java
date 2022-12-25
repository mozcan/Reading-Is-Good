package com.mozcan.readingIsGood.service;

import com.mozcan.readingIsGood.controller.dto.CustomerCreateRequest;
import com.mozcan.readingIsGood.dao.CustomerJpaRepository;
import com.mozcan.readingIsGood.exception.CustomerExistException;
import com.mozcan.readingIsGood.exception.CustomerNotFoundException;
import com.mozcan.readingIsGood.model.entity.CustomerEntity;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService{

    private final CustomerJpaRepository customerJpaRepository;
    private final PasswordEncoder passwordEncoder;

    public CustomerServiceImpl(CustomerJpaRepository customerJpaRepository, PasswordEncoder passwordEncoder) {
        this.customerJpaRepository = customerJpaRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public CustomerEntity create(CustomerCreateRequest createRequest) {
        if(customerJpaRepository.isUserExist(createRequest.getEmail()))
            throw new CustomerExistException("User has already been added");

        var customerEntity = new CustomerEntity();
        customerEntity.setEmail(createRequest.getEmail());
        customerEntity.setName(createRequest.getName());
        customerEntity.setPassword(passwordEncoder.encode(createRequest.getPassword()));

        var customer = customerJpaRepository.save(customerEntity);
        return customer;
    }

    @Override
    public CustomerEntity getCustomer(Long id) {
        Optional<CustomerEntity> customerEntity = customerJpaRepository.findById(id);
        if(customerEntity.isPresent())
            return customerEntity.get();

        throw new CustomerNotFoundException("Customer could not found");
    }
}
