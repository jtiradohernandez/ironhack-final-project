package com.example.ironproject.controller.Person;

import com.example.ironproject.controller.BaseTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ClientControllerTest extends BaseTest {
    @BeforeEach
    public void setUp() throws Exception {
        createTestingClients();
    }
    @AfterEach
    void tearDown() {
        clientRepository.deleteAll();
    }
}
