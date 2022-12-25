package com.mozcan.readingIsGood.controller.dto;

import javax.validation.constraints.NotNull;
import java.util.List;

public class OrderCreateRequest {
    @NotNull
    private Long customerId;

    @NotNull
    private List<Long> bookIds;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public List<Long> getBookIds() {
        return bookIds;
    }

    public void setBookIds(List<Long> bookIds) {
        this.bookIds = bookIds;
    }
}
