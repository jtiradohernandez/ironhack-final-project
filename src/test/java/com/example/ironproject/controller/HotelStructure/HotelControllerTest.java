package com.example.ironproject.controller.HotelStructure;

import com.example.ironproject.DTO.HotelStructure.BedroomDTO;
import com.example.ironproject.DTO.HotelStructure.HotelDTO;
import com.example.ironproject.model.HotelStructure.Hotel;
import com.example.ironproject.repository.HotelStructure.HotelRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class HotelControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private HotelRepository hotelRepository;
    private Hotel hotel;
    private Hotel hotel2;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private int hotelId;
    private int hotelId1;

    @BeforeEach
    public void setUp() {
        hotel = new Hotel("Hotel Outer Wilds", "Santuario","Superficie","Hondonada Frágil",100);
        hotelRepository.save(hotel);
        hotel2 = new Hotel("Wild Resort", "Estudio","Centro","Hondonada Frágil",100);
        hotelRepository.save(hotel2);
    }

    @AfterEach
    void tearDown() {
        hotelRepository.deleteAll();
    }

    @Test
    void userCanGetHotels() throws Exception{
        MvcResult mvcResult = this.mockMvc.perform(get("/hotel"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Hotel Outer Wilds"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Wild Resort"));
    }

    @Test
    void userCanGetHotelById() throws Exception{
        MvcResult mvcResult = this.mockMvc.perform(get("/hotel"+hotel.getHotelId()))
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
        MvcResult mvcResult = mockMvc.perform(post("/hotel").content(body).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Hotel Outer Wilds"));
    }

    void userCanAddMultipleHotels() throws Exception{
        hotelRepository.delete(hotel);
        hotelRepository.delete(hotel2);
        List<Hotel> hotelList = new ArrayList<Hotel>();
        hotelList.add(hotel);
        hotelList.add(hotel2);
        String body = objectMapper.writeValueAsString(hotelList);
        MvcResult mvcResult = mockMvc.perform(post("/hotel").content(body).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andReturn();
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
        MvcResult mvcResult = mockMvc.perform(patch("/hotel").content(body).contentType(MediaType.APPLICATION_JSON))
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
        String body = objectMapper.writeValueAsString(hotelList);
        MvcResult mvcResult = mockMvc.perform(patch("/hotel").content(body).contentType(MediaType.APPLICATION_JSON))
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
        MvcResult mvcResult = mockMvc.perform(delete("/hotel").content(body).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
        assertFalse(hotelRepository.findByHotelId(hotel.getHotelId()).isPresent());
    }

    @Test
    void userCanDeleteMultipleHotels() throws Exception{
        List<Integer> hotelList = new ArrayList<Integer>();
        hotelList.add(hotel.getHotelId());
        hotelList.add(hotel2.getHotelId());
        String body = objectMapper.writeValueAsString(hotelList);
        MvcResult mvcResult = mockMvc.perform(delete("/hotel").content(body).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
        assertFalse(hotelRepository.findByHotelId(hotel.getHotelId()).isPresent());
        assertFalse(hotelRepository.findByHotelId(hotel2.getHotelId()).isPresent());
    }
}