package com.mozcan.readingIsGood.controller.dto;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class BookCreateRequest {

    @NotNull(message = "Name cannot be null")
    private String name;

    @NotNull
    private String author;

    @NotNull
    private String publisher;

    @NotNull
    @Positive
    private Double price;

    @NotNull
    @Positive
    private Integer stock;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}
