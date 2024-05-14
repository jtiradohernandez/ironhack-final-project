package com.example.ironproject.controller.HotelStructure;

import com.example.ironproject.DTO.HotelStructure.BedroomDTO;
import com.example.ironproject.controller.BaseTest;
import com.example.ironproject.model.HotelStructure.Bedroom;
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
class BedroomControllerTest extends BaseTest {
    @BeforeEach
    public void setUp() throws Exception {
        createTestingHotels();
        createTestingBedrooms();
    }


    @Test
    void userCanGetBedrooms() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(get("/api/hotel/bedrooms").header("authorization", "Bearer " + token))
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
        int bedroomId = bedroom1.getRoomId();
        MvcResult mvcResult = this.mockMvc.perform(get("/api/hotel/bedrooms/"+bedroomId).header("authorization", "Bearer " + token))
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
        MvcResult mvcResult = this.mockMvc.perform(get("/api/hotel/"+hotel.getHotelId()+"/bedrooms").header("authorization", "Bearer " + token))
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
        List<Bedroom> bedroomList = new ArrayList<Bedroom>();
        bedroomList.add(bedroom1);
        String body = objectMapper.writeValueAsString(bedroomList);
        MvcResult mvcResult = mockMvc.perform(post("/api/hotel/bedrooms").content(body).contentType(MediaType.APPLICATION_JSON).header("authorization", "Bearer " + token)).andExpect(status().isCreated()).andReturn();
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
        MvcResult mvcResult = mockMvc.perform(post("/api/hotel/bedrooms").content(body).contentType(MediaType.APPLICATION_JSON).header("authorization", "Bearer " + token)).andExpect(status().isCreated()).andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("100"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("101"));
    }

    @Test
    void userCanUpdateBedroom() throws Exception{
        List<BedroomDTO> bedroomList = new ArrayList<BedroomDTO>();
        BedroomDTO values = new BedroomDTO();
        values.setRoomId(bedroom4.getRoomId());
        values.setCapacity(1);
        values.setRoomNumber(201);
        values.setFloor(2);
        bedroomList.add(values);
        String body = objectMapper.writeValueAsString(bedroomList);
        MvcResult mvcResult = mockMvc.perform(patch("/api/hotel/bedrooms").content(body).contentType(MediaType.APPLICATION_JSON).header("authorization", "Bearer " + token))
                .andExpect(status().isOk()).andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("201"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("2"));
    }

    @Test
    void userCanUpdateMultipleBedrooms() throws Exception{
        List<BedroomDTO> bedroomList = new ArrayList<BedroomDTO>();
        BedroomDTO values1 = new BedroomDTO();
        BedroomDTO values2 = new BedroomDTO();
        bedroomId = bedroom4.getRoomId();
        bedroomId1 = bedroom6.getRoomId();
        values1.setRoomId(bedroomId);
        values1.setCapacity(1);
        values1.setRoomNumber(201);
        values1.setFloor(2);
        values2.setRoomId(bedroomId1);
        values2.setCapacity(5);
        values2.setRoomNumber(202);
        values2.setFloor(2);
        bedroomList.add(values1);
        bedroomList.add(values2);
        String body = objectMapper.writeValueAsString(bedroomList);
        System.out.println(body);
        MvcResult mvcResult = mockMvc.perform(patch("/api/hotel/bedrooms").content(body).contentType(MediaType.APPLICATION_JSON).header("authorization", "Bearer " + token))
                .andExpect(status().isOk()).andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("201"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("2"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("202"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("5"));
    }

    @Test
    void userCanDeleteBedroom() throws Exception{
        List<Integer> roomList = new ArrayList<Integer>();
        Integer roomId = bedroom1.getRoomId();
        roomList.add(roomId);
        String body = objectMapper.writeValueAsString(roomList);
        MvcResult mvcResult = mockMvc.perform(delete("/api/hotel/bedrooms").content(body).contentType(MediaType.APPLICATION_JSON).header("authorization", "Bearer " + token))
                .andExpect(status().isOk()).andReturn();
        assertFalse(bedroomRepository.findByRoomId(bedroom1.getRoomId()).isPresent());
    }

    @Test
    void bedroomToDeleteDoesNotExist() throws Exception{
        List<Integer> roomList = new ArrayList<Integer>();
        Integer roomId = 1000;
        roomList.add(roomId);
        String body = objectMapper.writeValueAsString(roomList);
        MvcResult mvcResult = mockMvc.perform(delete("/api/hotel/bedrooms").content(body).contentType(MediaType.APPLICATION_JSON).header("authorization", "Bearer " + token))
                .andExpect(status().isNotFound()).andReturn();
        assertTrue(mvcResult.getResponse().getErrorMessage().contains("Bedroom 1000 is not found"));
    }

    @Test
    void userCanDeleteMultipleBedroom() throws Exception{
        List<Integer> roomList = new ArrayList<Integer>();
        roomList.add(bedroom1.getRoomId());
        roomList.add(bedroom3.getRoomId());
        roomList.add(bedroom4.getRoomId());
        String body = objectMapper.writeValueAsString(roomList);
        MvcResult mvcResult = mockMvc.perform(delete("/api/hotel/bedrooms").content(body).contentType(MediaType.APPLICATION_JSON).header("authorization", "Bearer " + token))
                .andExpect(status().isOk()).andReturn();
        assertFalse(bedroomRepository.findByRoomId(bedroom1.getRoomId()).isPresent());
        assertFalse(bedroomRepository.findByRoomId(bedroom3.getRoomId()).isPresent());
        assertFalse(bedroomRepository.findByRoomId(bedroom4.getRoomId()).isPresent());
    }
}