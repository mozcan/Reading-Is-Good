package com.mozcan.readingIsGood.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mozcan.readingIsGood.controller.CustomerController;
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
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

   @InjectMocks
   private CustomerController customerController;

   @BeforeEach
   void setUp() {
       MockitoAnnotations.openMocks(this);
       this.mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
   }

    @Test
    void customer_create_then_return_201_test() throws Exception {
        var createRequest = new CustomerCreateRequest("Mustafa ÖZCAN","wustafa.ozcan@gmail.com","12345");

        RequestBuilder request = MockMvcRequestBuilders.post("/customer").content(objectMapper.writeValueAsString(createRequest));
        MvcResult result = this.mockMvc.perform(request).andReturn();

        assertEquals("{\"customerId\":1,\"name\":\"Mustafa OZCAN\",\"email\":\"wustafa.ozcan2@gmail.com\"}",result.getResponse().getContentAsString());

    }
}
