package com.example.ironproject.Setup;


import com.example.ironproject.controller.BaseTest;
import com.example.ironproject.repository.Booking.BedroomBookingsRepository;
import com.example.ironproject.repository.Booking.FacilityBookingRepository;
import com.example.ironproject.repository.HotelStructure.BedroomRepository;
import com.example.ironproject.repository.HotelStructure.FacilityRepository;
import com.example.ironproject.repository.HotelStructure.HotelRepository;
import com.example.ironproject.repository.People.ClientRepository;
import com.example.ironproject.repository.People.EmployeeRepository;
import com.example.ironproject.repository.Security.RoleRepository;
import com.example.ironproject.service.People.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class Setup extends BaseTest {
    @Test
    void setup() throws Exception {
        createAllTesting();
    }

    @Test
    void cleanDatabase() throws Exception {
        bedroomBookingsRepository.deleteAll();
        facilityBookingRepository.deleteAll();
        clientRepository.deleteAll();
        employeeRepository.deleteAll();
        bedroomRepository.deleteAll();
        facilityRepository.deleteAll();
        hotelRepository.deleteAll();
        roleRepository.deleteAll();
    }
}
