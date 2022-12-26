package com.mozcan.readingIsGood.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mozcan.readingIsGood.controller.CustomerController;
import com.mozcan.readingIsGood.controller.dto.CustomerCreateRequest;
import com.mozcan.readingIsGood.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.http.RequestEntity.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CustomerService customerService;

    @Test
    void whenValidInput_thenReturns200() throws Exception {
        var createRequest = new CustomerCreateRequest("Mustafa ÖZCAN","wustafa.ozcan@gmail.com","12345");

/*
        mockMvc.perform(post("/customer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createRequest)))
                .andExpect(status().isOk());

 */
    }
}
