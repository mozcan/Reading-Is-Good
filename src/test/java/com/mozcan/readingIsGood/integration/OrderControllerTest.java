package com.mozcan.readingIsGood.integration;

import com.mozcan.readingIsGood.controller.CustomerController;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CustomerController.class)
public class OrderControllerTest {
}
