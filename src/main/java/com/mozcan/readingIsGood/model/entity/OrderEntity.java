package com.mozcan.readingIsGood.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;


import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "ORDER")
public class OrderEntity extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name="customer_id", nullable=false)
    @JsonIgnore
    private CustomerEntity customer;

    @Column(name = "ORDER_CREATED_TIME")
    private LocalDate orderCreatedTime;

    @ManyToMany(cascade = {
            CascadeType.PERSIST
    })
    @JoinTable(
            name = "ORDER_BOOK",
            joinColumns = { @JoinColumn(name = "order_id") },
            inverseJoinColumns = { @JoinColumn(name = "book_id") }
    )
    private List<BookEntity> books;


    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }
    public LocalDate getOrderCreatedTime() {
        return orderCreatedTime;
    }

    public void setOrderCreatedTime(LocalDate orderCreatedTime) {
        this.orderCreatedTime = orderCreatedTime;
    }

    public List<BookEntity> getBooks() {
        return books;
    }

    public void setBooks(List<BookEntity> books) {
        this.books = books;
    }

}
