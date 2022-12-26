package com.mozcan.readingIsGood.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mozcan.readingIsGood.controller.CustomerController;
import com.mozcan.readingIsGood.controller.OrderController;
import com.mozcan.readingIsGood.controller.dto.CustomerCreateRequest;
import com.mozcan.readingIsGood.controller.dto.OrderCreateRequest;
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

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CustomerController.class)
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @InjectMocks
    private OrderController orderController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();
    }

    @Test
    void customer_create_then_return_201_test() throws Exception {
        List<Long> bookIds = new ArrayList<>();
        bookIds.add(1L);
        bookIds.add(2L);

        var createRequest = new OrderCreateRequest(1L,bookIds);

        RequestBuilder request = MockMvcRequestBuilders.post("/order/create").content(objectMapper.writeValueAsString(createRequest));
        MvcResult result = this.mockMvc.perform(request).andReturn();

        assertEquals("{\"customer\":{\"customerId\":1,\"name\":\"Mustafa OZCAN\",\"email\":\"wustafa.ozcan2@gmail.com\"},\"bookSet\":[{\"bookId\":3,\"name\":\"İnce Memed 2\",\"author\":\"Yaşar Kemal\",\"publisher\":\"Yapı Kredi Yayınları\",\"price\":25.99,\"stock\":10},{\"bookId\":4,\"name\":\"İnce Memed 2\",\"author\":\"Yaşar Kemal\",\"publisher\":\"Yapı Kredi Yayınları\",\"price\":25.99,\"stock\":10}]}",result.getResponse().getContentAsString());

    }
}
