package com.mozcan.readingIsGood.service;

import com.mozcan.readingIsGood.controller.dto.OrderCreateRequest;
import com.mozcan.readingIsGood.controller.dto.StaticsCustomerMontlyOrderResponse;
import com.mozcan.readingIsGood.dao.OrderJpaRepository;
import com.mozcan.readingIsGood.exception.BookNotFoundException;
import com.mozcan.readingIsGood.exception.CustomerNotFoundException;
import com.mozcan.readingIsGood.exception.OrderNotFoundException;
import com.mozcan.readingIsGood.model.entity.OrderEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService{

    private final OrderJpaRepository orderJpaRepository;
    private final BookService bookService;
    private final CustomerService customerService;

    public OrderServiceImpl(OrderJpaRepository orderJpaRepository, BookService bookService, CustomerService customerService) {
        this.orderJpaRepository = orderJpaRepository;
        this.bookService = bookService;
        this.customerService = customerService;
    }

    @Override
    @Transactional
    public OrderEntity create(OrderCreateRequest createRequest)  throws CustomerNotFoundException, BookNotFoundException {
        var customer = customerService.getCustomer(createRequest.getCustomerId());
        var bookList = bookService.getBookList(createRequest.getBookIds());

        var orderEntity = new OrderEntity();

        orderEntity.setBooks(bookList);
        orderEntity.setCustomer(customer);
        orderEntity.setOrderCreatedTime(LocalDate.now());

        orderJpaRepository.save(orderEntity);

        bookService.updateBookStock(createRequest.getBookIds());

        return orderEntity;
    }

    @Override
    public Page<OrderEntity> getOrders(Long customerId, Pageable pageable) {
        var pageOrderEntity = orderJpaRepository.getCustomerOrders(customerId,pageable);
        return pageOrderEntity;
    }

    @Override
    public OrderEntity getOrder(Long id) {
        Optional<OrderEntity> orderEntity = orderJpaRepository.findById(id);
        if(orderEntity.isPresent())
            return orderEntity.get();

        throw new OrderNotFoundException("Order could not found");
    }

    @Override
    public List<StaticsCustomerMontlyOrderResponse> getCustomerMontlyOrder(Long customerId) {
        var statics = orderJpaRepository.getCustomerMontlyOrder(customerId);
        return statics;
    }
}
