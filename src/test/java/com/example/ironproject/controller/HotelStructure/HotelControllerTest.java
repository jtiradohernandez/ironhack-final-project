package com.example.ironproject.controller.HotelStructure;

import com.example.ironproject.DTO.HotelStructure.HotelDTO;
import com.example.ironproject.controller.BaseTest;
import com.example.ironproject.model.HotelStructure.Hotel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class HotelControllerTest extends BaseTest {

    @BeforeEach
    public void setUp() throws Exception {
        createTestingHotels();
    }


    @Test
    void userCanGetHotels() throws Exception{
        MvcResult mvcResult = this.mockMvc.perform(get("/api/hotel").header("authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Hotel Outer Wilds"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Wild Resort"));
    }

    @Test
    void userCanGetHotelById() throws Exception{
        int hotelId = hotel.getHotelId();
        MvcResult mvcResult = this.mockMvc.perform(get("/api/hotel/"+hotelId).header("authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Hotel Outer Wilds"));
        assertFalse(mvcResult.getResponse().getContentAsString().contains("Wild Resort"));
    }

    @Test
    void userCanAddHotel() throws Exception{
        hotelRepository.delete(hotel);
        List<Hotel> hotelList = new ArrayList<Hotel>();
        hotelList.add(hotel);
        String body = objectMapper.writeValueAsString(hotelList);
        MvcResult mvcResult = mockMvc.perform(post("/api/hotel").content(body).contentType(MediaType.APPLICATION_JSON).header("authorization", "Bearer " + token)).andExpect(status().isCreated()).andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Hotel Outer Wilds"));
    }

    void userCanAddMultipleHotels() throws Exception{
        hotelRepository.delete(hotel);
        hotelRepository.delete(hotel2);
        List<Hotel> hotelList = new ArrayList<Hotel>();
        hotelList.add(hotel);
        hotelList.add(hotel2);
        String body = objectMapper.writeValueAsString(hotelList);
        MvcResult mvcResult = mockMvc.perform(post("/api/hotel").content(body).contentType(MediaType.APPLICATION_JSON).header("authorization", "Bearer " + token)).andExpect(status().isCreated()).andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Hotel Outer Wilds"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Wild Resort"));
    }

    @Test
    void userCanUpdateHotel() throws Exception{
        List<HotelDTO> hotelList = new ArrayList<HotelDTO>();
        HotelDTO values = new HotelDTO();
        hotelId = hotel.getHotelId();
        values.setHotelId(hotelId);
        values.setAddress("Una direccion cualquiera");
        values.setName("Hotel Outer Wilds edited");
        values.setPlanet("Rocaterra");
        values.setRegion("Cuevas");
        values.setCapacity(500);
        hotelList.add(values);
        String body = objectMapper.writeValueAsString(hotelList);
        MvcResult mvcResult = mockMvc.perform(patch("/api/hotel").content(body).contentType(MediaType.APPLICATION_JSON).header("authorization", "Bearer " + token))
                .andExpect(status().isOk()).andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Una direccion cualquiera"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Hotel Outer Wilds edited"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Rocaterra"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Cuevas"));
    }

    @Test
    void userCanUpdateMultipleHotels() throws Exception{
        List<HotelDTO> hotelList = new ArrayList<HotelDTO>();
        HotelDTO values = new HotelDTO();
        HotelDTO values1 = new HotelDTO();
        hotelId = hotel.getHotelId();
        hotelId1 = hotel2.getHotelId();
        values.setHotelId(hotelId);
        values.setAddress("Una direccion cualquiera");
        values.setName("Hotel Outer Wilds edited");
        values.setPlanet("Rocaterra");
        values.setRegion("Cuevas");
        values.setCapacity(500);
        values1.setHotelId(hotelId1);
        values1.setAddress("Una direccion cualquiera pero para el hotel 2");
        values1.setName("Wild Resort edited");
        values1.setPlanet("Rocaterra tambien");
        values1.setRegion("Nucleo");
        values1.setCapacity(300);
        hotelList.add(values);
        hotelList.add(values1);
        String body = objectMapper.writeValueAsString(hotelList);
        MvcResult mvcResult = mockMvc.perform(patch("/api/hotel").content(body).contentType(MediaType.APPLICATION_JSON).header("authorization", "Bearer " + token))
                .andExpect(status().isOk()).andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Una direccion cualquiera"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Hotel Outer Wilds edited"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Rocaterra"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Cuevas"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("500"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Una direccion cualquiera pero para el hotel 2"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Wild Resort edited"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Rocaterra tambien"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Nucleo"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("300"));
    }

    @Test
    void userCanDeleteHotel() throws Exception{
        List<Integer> hotelList = new ArrayList<Integer>();
        Integer hotelId = hotel.getHotelId();
        hotelList.add(hotelId);
        String body = objectMapper.writeValueAsString(hotelList);
        MvcResult mvcResult = mockMvc.perform(delete("/api/hotel").content(body).contentType(MediaType.APPLICATION_JSON).header("authorization", "Bearer " + token))
                .andExpect(status().isOk()).andReturn();
        assertFalse(hotelRepository.findByHotelId(hotel.getHotelId()).isPresent());
    }

    @Test
    void hotelToDeleteDoesNotExist() throws Exception{
        List<Integer> hotelList = new ArrayList<Integer>();
        Integer hotelId = 1000;
        hotelList.add(hotelId);
        String body = objectMapper.writeValueAsString(hotelList);
        MvcResult mvcResult = mockMvc.perform(delete("/api/hotel").content(body).contentType(MediaType.APPLICATION_JSON).header("authorization", "Bearer " + token))
                .andExpect(status().isNotFound()).andReturn();
        assertTrue(mvcResult.getResponse().getErrorMessage().contains("Hotel 1000 is not found"));
    }

    @Test
    void userCanDeleteMultipleHotels() throws Exception{
        List<Integer> hotelList = new ArrayList<Integer>();
        hotelList.add(hotel.getHotelId());
        hotelList.add(hotel2.getHotelId());
        String body = objectMapper.writeValueAsString(hotelList);
        MvcResult mvcResult = mockMvc.perform(delete("/api/hotel").content(body).contentType(MediaType.APPLICATION_JSON).header("authorization", "Bearer " + token))
                .andExpect(status().isOk()).andReturn();
        assertFalse(hotelRepository.findByHotelId(hotel.getHotelId()).isPresent());
        assertFalse(hotelRepository.findByHotelId(hotel2.getHotelId()).isPresent());
    }
}