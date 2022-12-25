package com.mozcan.readingIsGood.service;

import com.mozcan.readingIsGood.controller.dto.BookCreateRequest;
import com.mozcan.readingIsGood.controller.dto.BookUpdateRequest;
import com.mozcan.readingIsGood.dao.BookJpaRepository;
import com.mozcan.readingIsGood.exception.BookNotFoundException;
import com.mozcan.readingIsGood.exception.OrderNotFoundException;
import com.mozcan.readingIsGood.model.entity.BookEntity;
import com.mozcan.readingIsGood.model.entity.OrderEntity;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private final BookJpaRepository bookJpaRepository;

    public BookServiceImpl(BookJpaRepository bookJpaRepository) {
        this.bookJpaRepository = bookJpaRepository;
    }

    @Override
    @Transactional
    public BookEntity create(BookCreateRequest createRequest) {
        var bookEntity = new BookEntity();

        bookEntity.setName(createRequest.getName());
        bookEntity.setAuthor(createRequest.getAuthor());
        bookEntity.setPublisher(createRequest.getPublisher());
        bookEntity.setPrice(createRequest.getPrice());
        bookEntity.setStock(createRequest.getStock());

        var book = bookJpaRepository.save(bookEntity);
        return book;
    }

    @Override
    @Transactional
    public BookEntity update(BookUpdateRequest updateRequest) {
        if(bookJpaRepository.isBookExist(updateRequest.getId())) {

            var bookEntity = new BookEntity();

            bookEntity.setId(updateRequest.getId());
            bookEntity.setName(updateRequest.getName());
            bookEntity.setAuthor(updateRequest.getAuthor());
            bookEntity.setPublisher(updateRequest.getPublisher());
            bookEntity.setPrice(updateRequest.getPrice());
            bookEntity.setStock(updateRequest.getStock());

            return bookJpaRepository.save(bookEntity);
        }

        throw new BookNotFoundException("Book could not found");
    }

    @Override
    public BookEntity getBook(Long id) {
        Optional<BookEntity> bookEntity = bookJpaRepository.findById(id);
        if(bookEntity.isPresent())
            return bookEntity.get();

        throw new BookNotFoundException("Book could not found");
    }

    @Override
    public List<BookEntity> getBookList(List<Long> bookIds) {
        List<Book> bookList = new ArrayList<>();

        var bookEntityList = bookJpaRepository.getBookList(bookIds);
        if(bookEntityList.size() != bookIds.size()) {
            var bookEntityIdList = bookEntityList.stream().map(BookEntity::getId).collect(Collectors.toList());

            List<Long> differences = bookIds.stream()
                    .filter(element -> !bookEntityIdList.contains(element))
                    .collect(Collectors.toList());

            if(differences.size() > 0)
                throw new BookNotFoundException("Some of the books were not found");
        }

        return bookEntityList;
    }

    @Override
    @Transactional
    public void updateBookStock(List<Long> bookIds) {
        bookJpaRepository.updateBookStock(bookIds);
    }
}
