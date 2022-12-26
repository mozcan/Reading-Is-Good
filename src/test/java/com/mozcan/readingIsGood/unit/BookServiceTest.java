package com.mozcan.readingIsGood.unit;

import com.mozcan.readingIsGood.controller.dto.BookCreateRequest;
import com.mozcan.readingIsGood.dao.BookJpaRepository;
import com.mozcan.readingIsGood.model.entity.BookEntity;
import com.mozcan.readingIsGood.service.BookServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @InjectMocks
    private BookServiceImpl bookService;
    @Mock
    private BookJpaRepository repository;

    @Test
    public void get_book_with_id_test() {

        var bookEntity = new BookEntity();
        bookEntity.setName("İnce Memed");
        bookEntity.setAuthor("Yaşar Kemal");
        bookEntity.setPublisher("YKY Yayınları");
        bookEntity.setPrice(23.99);
        bookEntity.setStock(25);

        when(repository.findById(1L)).thenReturn(Optional.of(bookEntity));
        var book = bookService.getBook(1L);

        assertEquals(bookEntity,book);
    }

    @Test
    public void get_book_list_with_ids_test() {

        List<Long> bookIds = new ArrayList<>();
        bookIds.add(1l);
        bookIds.add(2L);

        List<BookEntity> bookEntityList = new ArrayList<>();

        var bookEntity = new BookEntity();
        bookEntity.setId(1L);
        bookEntity.setName("İnce Memed");
        bookEntity.setAuthor("Yaşar Kemal");
        bookEntity.setPublisher("YKY Yayınları");
        bookEntity.setPrice(23.99);
        bookEntity.setStock(25);

        var bookEntity2 = new BookEntity();
        bookEntity.setId(2L);
        bookEntity.setName("İnce Memed 2");
        bookEntity.setAuthor("Yaşar Kemal");
        bookEntity.setPublisher("YKY Yayınları");
        bookEntity.setPrice(25.99);
        bookEntity.setStock(35);

        bookEntityList.add(bookEntity);
        bookEntityList.add(bookEntity2);

        when(repository.getBookList(bookIds)).thenReturn(bookEntityList);


        var bookList = bookService.getBookList(bookIds);

        assertEquals(bookEntityList,bookList);
    }

    @Test
    public void create_book_test() {
        var request = new BookCreateRequest("İnce Memed 3","Yaşar Kemal","YKY Yayınları",27.99,20);

        var bookEntity = new BookEntity();
        bookEntity.setName("İnce Memed 2");
        bookEntity.setAuthor("Yaşar Kemal");
        bookEntity.setPublisher("YKY Yayınları");
        bookEntity.setPrice(25.99);
        bookEntity.setStock(35);

        when(repository.save(bookEntity)).thenReturn(bookEntity);

        var savedBook = bookService.create(request);

        assertEquals(bookEntity,savedBook);
    }
}
