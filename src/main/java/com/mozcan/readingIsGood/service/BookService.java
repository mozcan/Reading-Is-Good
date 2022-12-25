package com.mozcan.readingIsGood.service;

import com.mozcan.readingIsGood.controller.dto.BookCreateRequest;
import com.mozcan.readingIsGood.controller.dto.BookUpdateRequest;
import com.mozcan.readingIsGood.model.entity.BookEntity;

import java.util.List;

public interface BookService {

    BookEntity create(BookCreateRequest createRequest);
    BookEntity update(BookUpdateRequest updateRequest);
    BookEntity getBook(Long id);
    List<BookEntity> getBookList(List<Long> bookIds);
    void updateBookStock(List<Long> bookIds);
}
