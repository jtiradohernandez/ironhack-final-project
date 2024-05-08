package com.example.ironproject.controller.Person;

import com.example.ironproject.controller.BaseTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class EmployeeControllerTest extends BaseTest {
    @BeforeEach
    public void setUp() throws Exception {
        createTestingEmployees();
    }
    @AfterEach
    void tearDown() {
        employeeRepository.deleteAll();
    }

    @Test
    void userCanGetEmployees() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(get("/api/hotel/employees").header("authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Solanum"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Ricard"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Victor"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Xavi"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Thais"));
        assertFalse(mvcResult.getResponse().getContentAsString().contains("Gabbro"));
        assertFalse(mvcResult.getResponse().getContentAsString().contains("Chert"));
    }

    @Test
    void userCanGetEmployeeById() throws Exception {
        employeeId = employee1.getDNI();
        MvcResult mvcResult = this.mockMvc.perform(get("/api/hotel/employees/"+employeeId).header("authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Solanum"));
        assertFalse(mvcResult.getResponse().getContentAsString().contains("Ricard"));
        assertFalse(mvcResult.getResponse().getContentAsString().contains("Victor"));
        assertFalse(mvcResult.getResponse().getContentAsString().contains("Xavi"));
        assertFalse(mvcResult.getResponse().getContentAsString().contains("Thais"));
        assertFalse(mvcResult.getResponse().getContentAsString().contains("Gabbro"));
        assertFalse(mvcResult.getResponse().getContentAsString().contains("Chert"));
    }

    @Test
    void userCanAddEmployee() throws Exception{
        employeeRepository.delete(employee1);
        String body = objectMapper.writeValueAsString(employee1);
        MvcResult mvcResult = mockMvc.perform(post("/api/hotel/employees").content(body).contentType(MediaType.APPLICATION_JSON).header("authorization", "Bearer " + token)).andExpect(status().isCreated()).andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Solanum"));
        assertTrue(employeeRepository.findByDNI(employee1.getDNI()).isPresent());
        assertFalse(mvcResult.getResponse().getContentAsString().contains("Ricard"));
        assertFalse(mvcResult.getResponse().getContentAsString().contains("Victor"));
    }


    @Test
    void userCanDeleteEmployee() throws Exception{
        employeeId = employee1.getDNI();
        String body = objectMapper.writeValueAsString(employeeId);
        mockMvc.perform(delete("/api/hotel/employees").content(body).contentType(MediaType.APPLICATION_JSON).header("authorization", "Bearer " + token))
                .andExpect(status().isOk()).andReturn();
        assertFalse(employeeRepository.findByDNI(employeeId).isPresent());
    }
}
