package com.mozcan.readingIsGood.utils;

import com.mozcan.readingIsGood.controller.dto.BookResponse;
import com.mozcan.readingIsGood.controller.dto.CustomerResponse;
import com.mozcan.readingIsGood.controller.dto.OrderResponse;
import com.mozcan.readingIsGood.model.entity.BookEntity;
import com.mozcan.readingIsGood.model.entity.CustomerEntity;
import com.mozcan.readingIsGood.model.entity.OrderEntity;

import java.util.ArrayList;
import java.util.List;

public class ModelConverter {

    public CustomerResponse customerEntityToCustomerResponse(CustomerEntity customerEntity) {
        var customerResponse = new CustomerResponse();
        customerResponse.setName(customerEntity.getName());
        customerResponse.setCustomerId(customerEntity.getId());
        customerResponse.setEmail(customerEntity.getEmail());

        return customerResponse;
    }

    public BookResponse bookEntityToBookResponse(BookEntity bookEntity) {
        var bookResponse =  new BookResponse();

        bookResponse.setBookId(bookEntity.getId());
        bookResponse.setName(bookEntity.getName());
        bookResponse.setAuthor(bookEntity.getAuthor());
        bookResponse.setPublisher(bookEntity.getPublisher());
        bookResponse.setStock(bookEntity.getStock());
        bookResponse.setPrice(bookEntity.getPrice());

        return bookResponse;
    }

    public List<BookResponse> bookEntityListToBookResponse(List<BookEntity> bookEntityList) {
        List<BookResponse> bookResponseList = new ArrayList<>();

        for(BookEntity bookEntity : bookEntityList) {
            var bookResponse =  new BookResponse();

            bookResponse.setBookId(bookEntity.getId());
            bookResponse.setName(bookEntity.getName());
            bookResponse.setAuthor(bookEntity.getAuthor());
            bookResponse.setPublisher(bookEntity.getPublisher());
            bookResponse.setStock(bookEntity.getStock());
            bookResponse.setPrice(bookEntity.getPrice());

            bookResponseList.add(bookResponse);
        }

        return bookResponseList;
    }

    public OrderResponse orderEntityToResponse(OrderEntity orderEntity) {
        var orderResponse = new OrderResponse();
        orderResponse.setCustomer(customerEntityToCustomerResponse(orderEntity.getCustomer()));
        orderResponse.setBookSet(bookEntityListToBookResponse(orderEntity.getBooks()));

        return orderResponse;
    }
}
