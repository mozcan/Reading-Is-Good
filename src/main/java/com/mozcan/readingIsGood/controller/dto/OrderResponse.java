package com.mozcan.readingIsGood.controller.dto;

import java.util.List;

public class OrderResponse {

    private CustomerResponse customer;
    private List<BookResponse> bookSet;

    public CustomerResponse getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerResponse customer) {
        this.customer = customer;
    }

    public List<BookResponse> getBookSet() {
        return bookSet;
    }

    public void setBookSet(List<BookResponse> bookSet) {
        this.bookSet = bookSet;
    }

    public OrderResponse(CustomerResponse customer, List<BookResponse> bookSet) {
        this.customer = customer;
        this.bookSet = bookSet;
    }

    public OrderResponse() {
    }
}
