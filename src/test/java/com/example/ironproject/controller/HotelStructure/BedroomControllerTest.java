package com.example.ironproject.controller.HotelStructure;

import com.example.ironproject.DTO.HotelStructure.BedroomDTO;
import com.example.ironproject.model.HotelStructure.Bedroom;
import com.example.ironproject.model.HotelStructure.Hotel;
import com.example.ironproject.repository.HotelStructure.BedroomRepository;
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
class BedroomControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private BedroomRepository bedroomRepository;
    @Autowired
    private HotelRepository hotelRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();
    Hotel hotel;
    Hotel hotel2;
    Bedroom bedroom1;
    Bedroom bedroom2;
    Bedroom bedroom3;
    Bedroom bedroom4;
    Bedroom bedroom5;
    Bedroom bedroom6;
    Bedroom bedroom7;
    int bedroomId;
    int bedroomId1;

    @BeforeEach
    public void setUp() {
        hotel = new Hotel("Hotel Outer Wilds", "Santuario","Superficie","Hondonada Frágil",100);
        hotelRepository.save(hotel);
        hotel2 = new Hotel("Wild Resort", "Estudio","Centro","Hondonada Frágil",100);
        hotelRepository.save(hotel2);
        bedroom1 = new Bedroom( hotel, 1, 2, 100 );
        bedroom2 = new Bedroom( hotel, 1, 2, 101 );
        bedroom3 = new Bedroom( hotel, 1, 2, 102 );
        bedroom4 = new Bedroom( hotel, 1, 3, 103 );
        bedroom5 = new Bedroom( hotel, 1, 4, 104 );
        bedroom6 = new Bedroom( hotel2, 1, 2, 100 );
        bedroom7 = new Bedroom( hotel2, 1, 4, 101 );
        bedroomRepository.save(bedroom1);
        bedroomRepository.save(bedroom2);
        bedroomRepository.save(bedroom3);
        bedroomRepository.save(bedroom4);
        bedroomRepository.save(bedroom5);
        bedroomRepository.save(bedroom6);
        bedroomRepository.save(bedroom7);
    }


    @AfterEach
    void tearDown() {
        bedroomRepository.deleteAll();
        hotelRepository.deleteAll();
    }

    @Test
    void userCanGetBedrooms() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(get("/hotel/bedrooms"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("100"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("101"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("102"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("103"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("104"));
        assertFalse(mvcResult.getResponse().getContentAsString().contains("105"));
        assertFalse(mvcResult.getResponse().getContentAsString().contains("99"));
    }

    @Test
    void userCanGetBedroomById() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(get("/hotel/bedrooms"+bedroom1.getRoomId()))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("100"));
        assertFalse(mvcResult.getResponse().getContentAsString().contains("101"));
        assertFalse(mvcResult.getResponse().getContentAsString().contains("102"));
        assertFalse(mvcResult.getResponse().getContentAsString().contains("103"));
        assertFalse(mvcResult.getResponse().getContentAsString().contains("104"));
        assertFalse(mvcResult.getResponse().getContentAsString().contains("105"));
        assertFalse(mvcResult.getResponse().getContentAsString().contains("99"));
    }

    @Test
    void userCanGetBedroomByHotelId() throws Exception{
        MvcResult mvcResult = this.mockMvc.perform(get("/hotel/"+hotel.getHotelId()+"/bedrooms"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Hotel Outer Wilds"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("100"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("101"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("102"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("103"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("104"));
        assertFalse(mvcResult.getResponse().getContentAsString().contains("Wild Resort"));
    }

    @Test
    void userCanAddBedroom() throws Exception{
        bedroomRepository.delete(bedroom1);
        String body = objectMapper.writeValueAsString(bedroom1);
        MvcResult mvcResult = mockMvc.perform(post("/hotel/bedrooms").content(body).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("100"));
    }

    @Test
    void userCanAddMultipleBedrooms() throws Exception {
        bedroomRepository.delete(bedroom1);
        bedroomRepository.delete(bedroom2);
        List<Bedroom> bedroomList = new ArrayList<Bedroom>();
        bedroomList.add(bedroom1);
        bedroomList.add(bedroom2);
        String body = objectMapper.writeValueAsString(bedroomList);
        MvcResult mvcResult = mockMvc.perform(post("/hotel/bedrooms").content(body).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("100"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("101"));
    }

    @Test
    void userCanUpdateBedroom() throws Exception{
        BedroomDTO values = new BedroomDTO();
        bedroomId = bedroom4.getRoomId();
        values.setRoomId(bedroomId);
        values.setAvailability(false);
        values.setCapacity(1);
        values.setRoomNumber(201);
        values.setFloor(2);
        String body = objectMapper.writeValueAsString(values);
        MvcResult mvcResult = mockMvc.perform(patch("/hotel/bedrooms").content(body).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("false"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("201"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("2"));
    }

    @Test
    void userCanUpdateMultipleBedrooms() throws Exception{
        List<BedroomDTO> bedroomList = new ArrayList<BedroomDTO>();
        BedroomDTO values1 = new BedroomDTO();
        BedroomDTO values2 = new BedroomDTO();
        bedroomId = bedroom4.getRoomId();
        bedroomId1 = bedroom4.getRoomId();
        values1.setRoomId(bedroomId);
        values1.setAvailability(false);
        values1.setCapacity(1);
        values1.setRoomNumber(201);
        values1.setFloor(2);
        values2.setRoomId(bedroomId1);
        values2.setAvailability(false);
        values2.setCapacity(5);
        values2.setRoomNumber(202);
        values2.setFloor(2);
        bedroomList.add(values1);
        bedroomList.add(values2);
        String body = objectMapper.writeValueAsString(bedroomList);
        MvcResult mvcResult = mockMvc.perform(patch("/hotel/bedrooms").content(body).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("false"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("201"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("2"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("202"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("5"));
    }

    @Test
    void userCanDeleteBedroom() throws Exception{
        Integer roomId = bedroom1.getRoomId();
        String body = objectMapper.writeValueAsString(roomId);
        MvcResult mvcResult = mockMvc.perform(delete("/hotel/bedrooms").content(body).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
        assertFalse(bedroomRepository.findByRoomId(bedroom1.getRoomId()).isPresent());
    }

    @Test
    void userCanDeleteMultipleBedroom() throws Exception{
        List<Integer> roomList = new ArrayList<Integer>();
        roomList.add(bedroom1.getRoomId());
        roomList.add(bedroom3.getRoomId());
        roomList.add(bedroom4.getRoomId());
        String body = objectMapper.writeValueAsString(roomList);
        MvcResult mvcResult = mockMvc.perform(delete("/hotel/bedrooms").content(body).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
        assertFalse(bedroomRepository.findByRoomId(bedroom1.getRoomId()).isPresent());
        assertFalse(bedroomRepository.findByRoomId(bedroom3.getRoomId()).isPresent());
        assertFalse(bedroomRepository.findByRoomId(bedroom4.getRoomId()).isPresent());
    }
}