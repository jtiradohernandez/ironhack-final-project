package com.example.ironproject.controller.Booking;

import com.example.ironproject.controller.BaseTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FacilityBookingsControllerTest extends BaseTest {
    @BeforeEach
    public void setUp() throws Exception {
        createTestingFacilityBookings();
    }
    @AfterEach
    void tearDown() {
        facilityBookingRepository.deleteAll();
    }
}
