package com.example.ironproject.controller.Booking;

import com.example.ironproject.DTO.Booking.BedroomBookingDTO;
import com.example.ironproject.DTO.Booking.FacilityBookingDTO;
import com.example.ironproject.controller.BaseTest;
import com.example.ironproject.enumeration.Service;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class FacilityBookingsControllerTest extends BaseTest {
    @BeforeEach
    public void setUp() throws Exception {
        createTestingHotels();
        createTestingFacilities();
        createRoles();
        createTestingEmployees();
        createTestingClients();
        createTestingFacilityBookings();
    }

    @AfterEach
    public void tearDown() throws Exception {
        facilityBookingRepository.deleteAll();
    }

    @Test
    void userCanGetFacilityBooking() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(get("/api/hotel/facilities/bookings").header("authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Gimnasio hondonada"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Gimnasio nomai"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Sauna"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Restaurante Hondonada"));
        assertFalse(mvcResult.getResponse().getContentAsString().contains("Restaurante Nomai"));
    }

    @Test
    void userCanGetFacilityBookingById() throws Exception {
        facilityBookingId = facilityBooking1.getBookingId();
        MvcResult mvcResult = this.mockMvc.perform(get("/api/hotel/facilities/bookings/"+facilityBookingId).header("authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Gimnasio hondonada"));
        assertFalse(mvcResult.getResponse().getContentAsString().contains("Gimnasio nomai"));
        assertFalse(mvcResult.getResponse().getContentAsString().contains("Sauna"));
        assertFalse(mvcResult.getResponse().getContentAsString().contains("Restaurante Hondonada"));
        assertFalse(mvcResult.getResponse().getContentAsString().contains("Restaurante Nomai"));
    }

    @Test
    void userCanGetFacilityBookingByHotelId() throws Exception {
        hotelId = hotel2.getHotelId();
        facilityBookingId = facilityBooking1.getBookingId();
        facilityBookingId1 = facilityBooking3.getBookingId();
        MvcResult mvcResult = this.mockMvc.perform(get("/api/hotel/"+hotelId+"/facilities/bookings").header("authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Feldespato"));
        assertFalse(mvcResult.getResponse().getContentAsString().contains("Esker"));
    }

    @Test
    void userCanAddFacilityBooking() throws Exception{
        facilityBookingRepository.delete(facilityBooking1);
        String body = objectMapper.writeValueAsString(facilityBooking1);
        MvcResult mvcResult = mockMvc.perform(post("/api/hotel/facilities/bookings").content(body).contentType(MediaType.APPLICATION_JSON).header("authorization", "Bearer " + token)).andExpect(status().isCreated()).andReturn();
        int bookingId = JsonPath.read(mvcResult.getResponse().getContentAsString(), "$.bookingId");
        assertTrue(facilityBookingRepository.findFacilityBookingByBookingId(bookingId).isPresent());
    }

    @Test
    void userCannotAddBedroomBooking() throws Exception{
        String body = objectMapper.writeValueAsString(facilityBooking1);
        MvcResult mvcResult = mockMvc.perform(post("/api/hotel/facilities/bookings").content(body).contentType(MediaType.APPLICATION_JSON).header("authorization", "Bearer " + token)).andExpect(status().isConflict()).andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("The facility is unavailable for the slot selected"));
    }

    @Test
    void userCanUpdateFacilityBooking() throws Exception{
        FacilityBookingDTO bookingToUpdate = new FacilityBookingDTO();
        facilityBookingId = facilityBooking1.getBookingId();
        Date slot = new Date(125, 9, 20);
        bookingToUpdate.setRoomBooked(sauna);
        bookingToUpdate.setClientOfBooking(client5);
        bookingToUpdate.setSlot(slot);
        bookingToUpdate.setService(Service.Cleaning);
        bookingToUpdate.setWorkerAssigned(employee3);
        String body = objectMapper.writeValueAsString(bookingToUpdate);
        MvcResult mvcResult = mockMvc.perform(patch("/api/hotel/facilities/bookings/"+facilityBookingId).content(body).contentType(MediaType.APPLICATION_JSON).header("authorization", "Bearer " + token))
                .andExpect(status().isOk()).andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Sauna"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Thais Real"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Cleaning"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Chert"));
    }

    @Test
    void facilityBookingToUpdateDoesNotExist() throws Exception{
        FacilityBookingDTO bookingToUpdate = new FacilityBookingDTO();
        facilityBookingId = 1000;
        Date slot = new Date(125, 9, 20);
        bookingToUpdate.setRoomBooked(sauna);
        bookingToUpdate.setClientOfBooking(client5);
        bookingToUpdate.setSlot(slot);
        bookingToUpdate.setService(Service.Cleaning);
        bookingToUpdate.setWorkerAssigned(employee3);
        String body = objectMapper.writeValueAsString(bookingToUpdate);
        MvcResult mvcResult = mockMvc.perform(patch("/api/hotel/facilities/bookings/"+facilityBookingId).content(body).contentType(MediaType.APPLICATION_JSON).header("authorization", "Bearer " + token))
                .andExpect(status().isNotFound()).andReturn();
        assertTrue(mvcResult.getResponse().getErrorMessage().contains("Reservation 1000 is not found"));
    }

    @Test
    void userCanDeleteFacilityBooking() throws Exception{
        facilityBookingId = facilityBooking1.getBookingId();
        mockMvc.perform(delete("/api/hotel/facilities/bookings/"+facilityBookingId).contentType(MediaType.APPLICATION_JSON).header("authorization", "Bearer " + token))
                .andExpect(status().isOk()).andReturn();
        assertFalse(facilityBookingRepository.findFacilityBookingByBookingId(facilityBookingId).isPresent());
    }

    @Test
    void facilityBookingToDeleteDoesNotExist() throws Exception{
        facilityBookingId = 10000;
        MvcResult mvcResult = mockMvc.perform(delete("/api/hotel/facilities/bookings/"+facilityBookingId).contentType(MediaType.APPLICATION_JSON).header("authorization", "Bearer " + token))
                .andExpect(status().isNotFound()).andReturn();
        assertTrue(mvcResult.getResponse().getErrorMessage().contains("Reservation 10000 is not found"));
    }
}
