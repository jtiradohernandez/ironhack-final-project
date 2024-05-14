package com.example.ironproject.controller.Person;

import com.example.ironproject.DTO.People.ClientDTO;
import com.example.ironproject.controller.BaseTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class ClientControllerTest extends BaseTest {
    @BeforeEach
    public void setUp() throws Exception {
        createTestingClients();
    }
    /*@AfterEach
    void tearDown() {
        clientRepository.deleteAll();
    }*/

    @Test
    void userCanGetClients() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(get("/api/hotel/clients").header("authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Esker"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Riebeck"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Feldespato"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Gabbro"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Chert"));
        assertFalse(mvcResult.getResponse().getContentAsString().contains("Xavi"));
        assertFalse(mvcResult.getResponse().getContentAsString().contains("Thais"));
    }

    @Test
    void userCanGetClientById() throws Exception {
        clientId = client1.getDNI();
        MvcResult mvcResult = this.mockMvc.perform(get("/api/hotel/clients/"+clientId).header("authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Esker"));
        assertFalse(mvcResult.getResponse().getContentAsString().contains("Riebeck"));
        assertFalse(mvcResult.getResponse().getContentAsString().contains("Feldespato"));
        assertFalse(mvcResult.getResponse().getContentAsString().contains("Gabbro"));
        assertFalse(mvcResult.getResponse().getContentAsString().contains("Chert"));
        assertFalse(mvcResult.getResponse().getContentAsString().contains("Xavi"));
        assertFalse(mvcResult.getResponse().getContentAsString().contains("Thais"));
    }

    @Test
    void userCanAddClient() throws Exception{
        clientRepository.delete(client1);
        String body = objectMapper.writeValueAsString(client1);
        MvcResult mvcResult = mockMvc.perform(post("/api/hotel/clients").content(body).contentType(MediaType.APPLICATION_JSON).header("authorization", "Bearer " + token)).andExpect(status().isCreated()).andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Esker"));
        assertTrue(clientRepository.findByDNI(client1.getDNI()).isPresent());
        assertFalse(mvcResult.getResponse().getContentAsString().contains("Riebeck"));
        assertFalse(mvcResult.getResponse().getContentAsString().contains("Feldespato"));
    }

    @Test
    void userCanUpdateClient() throws Exception{
        ClientDTO clientToUpdate = new ClientDTO();
        clientId = client1.getDNI();
        clientToUpdate.setDNI(clientId);
        clientToUpdate.setOrigin("Asia");
        String body = objectMapper.writeValueAsString(clientToUpdate);
        MvcResult mvcResult = mockMvc.perform(patch("/api/hotel/clients").content(body).contentType(MediaType.APPLICATION_JSON).header("authorization", "Bearer " + token))
                .andExpect(status().isOk()).andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Asia"));
        assertFalse(mvcResult.getResponse().getContentAsString().contains("Lumbre"));
    }

    @Test
    void clientToUpdateDoesNotExist() throws Exception{
        ClientDTO clientToUpdate = new ClientDTO();
        clientId = "1000";
        clientToUpdate.setDNI(clientId);
        clientToUpdate.setOrigin("Asia");
        String body = objectMapper.writeValueAsString(clientToUpdate);
        MvcResult mvcResult = mockMvc.perform(patch("/api/hotel/clients").content(body).contentType(MediaType.APPLICATION_JSON).header("authorization", "Bearer " + token))
                .andExpect(status().isNotFound()).andReturn();
        assertTrue(mvcResult.getResponse().getErrorMessage().contains("Client is not found"));
    }


    @Test
    void userCanDeleteClient() throws Exception{
        clientId = client1.getDNI();
        mockMvc.perform(delete("/api/hotel/clients/"+clientId).contentType(MediaType.APPLICATION_JSON).header("authorization", "Bearer " + token))
                .andExpect(status().isOk()).andReturn();
        assertFalse(clientRepository.findByDNI(clientId).isPresent());
    }

    @Test
    void clientToDeleteDoesNotExist() throws Exception{
        MvcResult mvcResult = mockMvc.perform(delete("/api/hotel/clients/1000").contentType(MediaType.APPLICATION_JSON).header("authorization", "Bearer " + token))
                .andExpect(status().isNotFound()).andReturn();
        assertTrue(mvcResult.getResponse().getErrorMessage().contains("Client 1000 is not found"));
    }
}
