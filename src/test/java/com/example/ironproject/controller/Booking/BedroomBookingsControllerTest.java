package com.example.ironproject.controller.Booking;

import com.example.ironproject.DTO.Booking.BedroomBookingDTO;
import com.example.ironproject.DTO.People.ClientDTO;
import com.example.ironproject.controller.BaseTest;
import com.jayway.jsonpath.JsonPath;
import org.junit.Assert;
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
class BedroomBookingsControllerTest extends BaseTest {
    @BeforeEach
    public void setUp() throws Exception {
        createTestingBedroomBookings();
    }
    @AfterEach
    void tearDown() {
        bedroomBookingsRepository.deleteAll();
    }

    @Test
    void userCanGetBedroomBooking() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(get("/api/hotel/bedrooms/bookings").header("authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Esker"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Riebeck"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Feldespato"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Gabbro"));
        assertFalse(mvcResult.getResponse().getContentAsString().contains("Chert"));
    }

    @Test
    void userCanGetBedroomBookingById() throws Exception {
        bedroomBookingId = bedroomBooking1.getBookingId();
        MvcResult mvcResult = this.mockMvc.perform(get("/api/hotel/bedrooms/bookings/"+bedroomBookingId).header("authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Esker"));
        assertFalse(mvcResult.getResponse().getContentAsString().contains("Riebeck"));
        assertFalse(mvcResult.getResponse().getContentAsString().contains("Feldespato"));
        assertFalse(mvcResult.getResponse().getContentAsString().contains("Gabbro"));
        assertFalse(mvcResult.getResponse().getContentAsString().contains("Chert"));
    }

    @Test
    void userCanGetBedroomBookingByHotelId() throws Exception {
        hotelId = hotel2.getHotelId();
        bedroomBookingId = bedroomBooking1.getBookingId();
        bedroomBookingId1 = bedroomBooking5.getBookingId();
        MvcResult mvcResult = this.mockMvc.perform(get("/api/hotel/"+hotelId+"/bedrooms/bookings").header("authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        assertFalse(mvcResult.getResponse().getContentAsString().contains(String.valueOf(bedroomBookingId)));
        assertTrue(mvcResult.getResponse().getContentAsString().contains(String.valueOf(bedroomBookingId1)));
    }

    @Test
    void userCanSeeAvailableBedrooms() throws Exception {
        hotelId = hotel.getHotelId();
        MvcResult mvcResult = this.mockMvc.perform(get("/api/hotel/"+hotelId+"/bedrooms/bookings/availability/2025-08-20/2025-08-29").header("authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("\"roomNumber\":"+bedroom1.getRoomNumber()));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("\"roomNumber\":"+bedroom2.getRoomNumber()));
        assertFalse(mvcResult.getResponse().getContentAsString().contains("\"roomNumber\":"+bedroom3.getRoomNumber()));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("\"roomNumber\":"+bedroom4.getRoomNumber()));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("\"roomNumber\":"+bedroom5.getRoomNumber()));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("\"roomNumber\":"+bedroom6.getRoomNumber()));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("\"roomNumber\":"+bedroom7.getRoomNumber()));
    }

    @Test
    void userCanAddBedroomBooking() throws Exception{
        bedroomBookingsRepository.delete(bedroomBooking1);
        String body = objectMapper.writeValueAsString(bedroomBooking1);
        MvcResult mvcResult = mockMvc.perform(post("/api/hotel/bedrooms/bookings").content(body).contentType(MediaType.APPLICATION_JSON).header("authorization", "Bearer " + token)).andExpect(status().isCreated()).andReturn();
        int bookingId = JsonPath.read(mvcResult.getResponse().getContentAsString(), "$.bookingId");
        assertTrue(bedroomBookingsRepository.findBedroomBookingsByBookingId(bookingId).isPresent());
    }

    @Test
    void userCannotAddBedroomBooking() throws Exception{
        String body = objectMapper.writeValueAsString(bedroomBooking1);
        MvcResult mvcResult = mockMvc.perform(post("/api/hotel/bedrooms/bookings").content(body).contentType(MediaType.APPLICATION_JSON).header("authorization", "Bearer " + token)).andExpect(status().isConflict()).andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("The bedroom is unavailable for the date selected"));
    }

    @Test
    void userCanUpdateBedroomBooking() throws Exception{
        BedroomBookingDTO bookingToUpdate = new BedroomBookingDTO();
        bedroomBookingId = bedroomBooking1.getBookingId();
        Date arrivalDate = new Date(125, 9, 20);
        Date departureDate = new Date(125, 9, 30);
        bookingToUpdate.setRoomBooked(bedroom4);
        bookingToUpdate.setClientOfBooking(client5);
        bookingToUpdate.setArrivalDate(arrivalDate);
        bookingToUpdate.setDepartureDate(departureDate);
        String body = objectMapper.writeValueAsString(bookingToUpdate);
        MvcResult mvcResult = mockMvc.perform(patch("/api/hotel/bedrooms/bookings/"+bedroomBookingId).content(body).contentType(MediaType.APPLICATION_JSON).header("authorization", "Bearer " + token))
                .andExpect(status().isOk()).andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("103"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Chert"));
    }

    @Test
    void userCanDeleteBedroomBooking() throws Exception{
        bedroomBookingId = bedroomBooking1.getBookingId();
        mockMvc.perform(delete("/api/hotel/bedrooms/bookings/"+bedroomBookingId).contentType(MediaType.APPLICATION_JSON).header("authorization", "Bearer " + token))
                .andExpect(status().isOk()).andReturn();
        assertFalse(bedroomBookingsRepository.findBedroomBookingsByBookingId(bedroomBookingId).isPresent());
    }
}
