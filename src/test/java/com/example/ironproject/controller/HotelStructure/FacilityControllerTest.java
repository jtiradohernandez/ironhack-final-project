package com.example.ironproject.controller.HotelStructure;

import com.example.ironproject.DTO.HotelStructure.BedroomDTO;
import com.example.ironproject.DTO.HotelStructure.FacilityDTO;
import com.example.ironproject.model.HotelStructure.Facility;
import com.example.ironproject.model.HotelStructure.Hotel;
import com.example.ironproject.repository.HotelStructure.FacilityRepository;
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
class FacilityControllerTest {
    @Autowired
    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private FacilityRepository facilityRepository;
    @Autowired
    private HotelRepository hotelRepository;
    private Hotel hotel;
    private Hotel hotel2;
    private Facility gym1;
    private Facility gym2;
    private Facility sauna;
    private Facility restaurant1;
    private Facility restaurant2;
    private int facilityId;
    private int facilityId1;

    @BeforeEach
    public void setUp() {
        hotel = new Hotel("Hotel Outer Wilds", "Santuario","Superficie","Hondonada Frágil",100);
        hotelRepository.save(hotel);
        hotel2 = new Hotel("Wild Resort", "Estudio","Centro","Hondonada Frágil",100);
        hotelRepository.save(hotel2);
        gym1 = new Facility( hotel,"Gimnasio hondonada", -1, true, 150);
        gym2 = new Facility( hotel2, "Gimnasio nomai",4, true, 60);
        sauna = new Facility( hotel,"Sauna", -1, true, 6 );
        restaurant1 = new Facility( hotel,"Restaurante Hondonada", 5, true, 100 );
        restaurant2 = new Facility( hotel2,"Restaurante Nomai", 0, true, 200 );
        facilityRepository.save(gym1);
        facilityRepository.save(gym2);
        facilityRepository.save(sauna);
        facilityRepository.save(restaurant1);
        facilityRepository.save(restaurant2);
    }

    @AfterEach
    void tearDown() {
        facilityRepository.deleteAll();
        hotelRepository.deleteAll();
    }

    @Test
    void userCanGetFacilities() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(get("/hotel/facilities"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Gimnasio hondonada"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Gimnasio nomai"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Sauna"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Restaurante Hondonada"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Restaurante Nomai"));
    }

    @Test
    void userCanGetFacilityById() throws Exception{
        MvcResult mvcResult = this.mockMvc.perform(get("/hotel/facilities/"+gym1.getRoomId()))
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
    void userCanGetFacilitiesByHotelId() throws Exception{
        MvcResult mvcResult = this.mockMvc.perform(get("/hotel/"+hotel.getHotelId()+"/facilities"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Gimnasio hondonada"));
        assertFalse(mvcResult.getResponse().getContentAsString().contains("Gimnasio nomai"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Sauna"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Restaurante Hondonada"));
        assertFalse(mvcResult.getResponse().getContentAsString().contains("Restaurante Nomai"));
    }

    @Test
    void userCanAddFacility() throws Exception{
        facilityRepository.delete(gym1);
        List<Facility> facilityList = new ArrayList<Facility>();
        facilityList.add(gym1);
        String body = objectMapper.writeValueAsString(facilityList);
        MvcResult mvcResult = mockMvc.perform(post("/hotel/facilities").content(body).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Gimnasio hondonada"));
    }

    @Test
    void userCanAddMultipleFacilities() throws Exception {
        facilityRepository.delete(gym1);
        facilityRepository.delete(gym2);
        List<Facility> facilityList = new ArrayList<Facility>();
        facilityList.add(gym1);
        facilityList.add(gym2);
        String body = objectMapper.writeValueAsString(facilityList);
        MvcResult mvcResult = mockMvc.perform(post("/hotel/facilities").content(body).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Gimnasio hondonada"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Gimnasio nomai"));
    }

    @Test
    void userCanUpdateFacility() throws Exception{
        List<FacilityDTO> facilityList = new ArrayList<FacilityDTO>();
        FacilityDTO values = new FacilityDTO();
        facilityId = sauna.getRoomId();
        values.setRoomId(facilityId);
        values.setName("sauna nueva");
        values.setCanBeBooked(false);
        //values.setOpeningHours(); TODO
        values.setCapacity(10);
        values.setFloor(4);
        facilityList.add(values);
        String body = objectMapper.writeValueAsString(facilityList);
        MvcResult mvcResult = mockMvc.perform(patch("/hotel/facilities").content(body).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("sauna nueva"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("false"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("10"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("4"));
    }

    @Test
    void userCanUpdateMultipleFacilities() throws Exception{
        List<FacilityDTO> facilityList = new ArrayList<FacilityDTO>();
        FacilityDTO values1 = new FacilityDTO();
        FacilityDTO values2 = new FacilityDTO();
        facilityId = sauna.getRoomId();
        facilityId1 = restaurant1.getRoomId();
        values1.setRoomId(facilityId);
        values1.setName("sauna nueva");
        values1.setCanBeBooked(false);
        //values.setOpeningHours(); TODO
        values1.setCapacity(10);
        values1.setFloor(4);
        values2.setRoomId(facilityId1);
        values2.setName("restaurant nuevo");
        values2.setCanBeBooked(false);
        //values.setOpeningHours(); TODO
        values2.setCapacity(1000);
        values2.setFloor(1);
        facilityList.add(values1);
        facilityList.add(values2);
        String body = objectMapper.writeValueAsString(facilityList);
        MvcResult mvcResult = mockMvc.perform(patch("/hotel/facilities").content(body).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("sauna nueva"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("false"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("10"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("4"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("restaurant nuevo"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("1000"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("1"));
    }

    @Test
    void userCanDeleteFacility() throws Exception{
        List<Integer> roomList = new ArrayList<Integer>();
        Integer roomId = restaurant1.getRoomId();
        roomList.add(roomId);
        String body = objectMapper.writeValueAsString(roomList);
        MvcResult mvcResult = mockMvc.perform(delete("/hotel/facilities").content(body).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
        assertFalse(facilityRepository.findByRoomId(restaurant1.getRoomId()).isPresent());
    }

    @Test
    void userCanDeleteMultipleFacilities() throws Exception{
        List<Integer> roomList = new ArrayList<Integer>();
        roomList.add(restaurant1.getRoomId());
        roomList.add(restaurant2.getRoomId());
        roomList.add(sauna.getRoomId());
        String body = objectMapper.writeValueAsString(roomList);
        MvcResult mvcResult = mockMvc.perform(delete("/hotel/facilities").content(body).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
        assertFalse(facilityRepository.findByRoomId(restaurant1.getRoomId()).isPresent());
        assertFalse(facilityRepository.findByRoomId(restaurant2.getRoomId()).isPresent());
        assertFalse(facilityRepository.findByRoomId(sauna.getRoomId()).isPresent());
    }
}