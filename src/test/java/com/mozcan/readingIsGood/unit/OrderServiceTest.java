package com.mozcan.readingIsGood.unit;

import com.mozcan.readingIsGood.controller.dto.CustomerCreateRequest;
import com.mozcan.readingIsGood.controller.dto.OrderCreateRequest;
import com.mozcan.readingIsGood.dao.BookJpaRepository;
import com.mozcan.readingIsGood.dao.OrderJpaRepository;
import com.mozcan.readingIsGood.model.entity.BookEntity;
import com.mozcan.readingIsGood.model.entity.CustomerEntity;
import com.mozcan.readingIsGood.model.entity.OrderEntity;
import com.mozcan.readingIsGood.service.BookServiceImpl;
import com.mozcan.readingIsGood.service.CustomerServiceImpl;
import com.mozcan.readingIsGood.service.OrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @InjectMocks
    private OrderServiceImpl orderService;

    @Mock
    private OrderJpaRepository repository;

    @Test
    public void get_order_by_id_test() {
        var orderEntity = new OrderEntity();

        var customerEntity = new CustomerEntity();
        customerEntity.setName("Mustafa");
        customerEntity.setEmail("wustafa.ozcan@gmail.com");
        customerEntity.setPassword("12345");

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

        orderEntity.setOrderCreatedTime(LocalDate.now());
        orderEntity.setCustomer(customerEntity);
        orderEntity.setBooks(bookEntityList);

        when(repository.findById(1L)).thenReturn(Optional.of(orderEntity));
        var order = orderService.getOrder(1L);

        assertEquals(orderEntity,order);
    }

    @Test
    public void create_order_test() {
        List<Long> bookIds = new ArrayList<>();
        bookIds.add(1L);
        bookIds.add(2L);

        var request = new OrderCreateRequest(1L,bookIds);

        var orderEntity = new OrderEntity();

        var customerEntity = new CustomerEntity();
        customerEntity.setName("Mustafa");
        customerEntity.setEmail("wustafa.ozcan@gmail.com");
        customerEntity.setPassword("12345");

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

        orderEntity.setCustomer(customerEntity);
        orderEntity.setBooks(bookEntityList);
        orderEntity.setOrderCreatedTime(LocalDate.now());

        when(repository.save(orderEntity)).thenReturn(orderEntity);

        var savedOrder = orderService.create(request);

        assertEquals(orderEntity,savedOrder);
    }
}
