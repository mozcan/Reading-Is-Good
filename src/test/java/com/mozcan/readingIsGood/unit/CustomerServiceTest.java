package com.mozcan.readingIsGood.unit;

import com.mozcan.readingIsGood.controller.dto.CustomerCreateRequest;
import com.mozcan.readingIsGood.dao.CustomerJpaRepository;
import com.mozcan.readingIsGood.model.entity.CustomerEntity;
import com.mozcan.readingIsGood.service.CustomerServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    @InjectMocks
    private CustomerServiceImpl customerService;
    @Mock
    private CustomerJpaRepository repository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Test
    public void is_customer_exist_test() {
        when(repository.isUserExist("wustafa.ozcan@gmail.com")).thenReturn(Boolean.TRUE);
        var isUserExist = customerService.isUserExist("wustafa.ozcan@gmail.com");

        assertEquals(Boolean.TRUE, isUserExist);
    }

    @Test
    public void get_customer_by_id_test() {

        var customerEntity = new CustomerEntity();
        customerEntity.setName("Mustafa");
        customerEntity.setEmail("wustafa.ozcan@gmail.com");
        customerEntity.setPassword("12345");

        when(repository.findById(1L)).thenReturn(Optional.of(customerEntity));
        var customer = customerService.getCustomer(1L);

        assertEquals(customerEntity,customer);
    }

    @Test
    public void create_customer_test() {
        var request = new CustomerCreateRequest("Mustafa","wustafa.ozcan@gmail.com",passwordEncoder.encode("12345"));

        var customerEntity = new CustomerEntity();
        customerEntity.setName("Mustafa");
        customerEntity.setEmail("wustafa.ozcan@gmail.com");
        customerEntity.setPassword("12345");

        when(repository.save(customerEntity)).thenReturn(customerEntity);

        var savedCustomer = customerService.create(request);

        assertEquals(customerEntity,savedCustomer);
    }
}
