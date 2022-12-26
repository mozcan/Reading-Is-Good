package com.mozcan.readingIsGood.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mozcan.readingIsGood.controller.BookController;
import com.mozcan.readingIsGood.controller.CustomerController;
import com.mozcan.readingIsGood.controller.dto.BookCreateRequest;
import com.mozcan.readingIsGood.controller.dto.CustomerCreateRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CustomerController.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @InjectMocks
    private BookController bookController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
    }

    @Test
    void book_create_then_return_201_test() throws Exception {
        var createRequest = new BookCreateRequest("İnce Memed 3","Yaşar Kemal","YKY Yayınları",27.99,20);

        RequestBuilder request = MockMvcRequestBuilders.post("/book/create").content(objectMapper.writeValueAsString(createRequest));
        MvcResult result = this.mockMvc.perform(request).andReturn();

        assertEquals("{\"bookId\":1,\"name\":\"İnce Memed 2\",\"author\":\"Yaşar Kemal\",\"publisher\":\"Yapı Kredi Yayınları\",\"price\":25.99,\"stock\":10}",result.getResponse().getContentAsString());

    }

    @Test
    void get_book_with_id_test() throws Exception {

        RequestBuilder request = MockMvcRequestBuilders.post("/{id}",1L);
        MvcResult result = this.mockMvc.perform(request).andReturn();

        assertEquals("{\"bookId\":1,\"name\":\"İnce Memed 2\",\"author\":\"Yaşar Kemal\",\"publisher\":\"Yapı Kredi Yayınları\",\"price\":25.99,\"stock\":10}",result.getResponse().getContentAsString());

    }
}
