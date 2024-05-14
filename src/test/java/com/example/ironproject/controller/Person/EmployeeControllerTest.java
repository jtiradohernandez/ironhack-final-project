package com.example.ironproject.controller.Person;

import com.example.ironproject.DTO.People.EmployeeDTO;
import com.example.ironproject.controller.BaseTest;
import com.example.ironproject.enumeration.Jobs;
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
        createRoles();
        createTestingEmployees();
    }
    /*@AfterEach
    void tearDown() {
        employeeRepository.deleteAll();
    }*/

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
    void userCanUpdateEmployee() throws Exception{
        createTestingHotels();
        EmployeeDTO employeeToUpdate = new EmployeeDTO();
        employeeId = employee1.getDNI();
        employeeToUpdate.setDNI(employeeId);
        employeeToUpdate.setJob(Jobs.Cleaning);
        employeeToUpdate.setHotelAssigned(hotel);
        String body = objectMapper.writeValueAsString(employeeToUpdate);
        MvcResult mvcResult = mockMvc.perform(patch("/api/hotel/employees").content(body).contentType(MediaType.APPLICATION_JSON).header("authorization", "Bearer " + token))
                .andExpect(status().isOk()).andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Cleaning"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Hotel Outer Wilds"));
    }

    @Test
    void employeeToUpdateDoesNotExist() throws Exception{
        createTestingHotels();
        EmployeeDTO employeeToUpdate = new EmployeeDTO();
        employeeId = "qwerqwerqwerqwer";
        employeeToUpdate.setDNI(employeeId);
        employeeToUpdate.setJob(Jobs.Cleaning);
        employeeToUpdate.setHotelAssigned(hotel);
        String body = objectMapper.writeValueAsString(employeeToUpdate);
        MvcResult mvcResult = mockMvc.perform(patch("/api/hotel/employees").content(body).contentType(MediaType.APPLICATION_JSON).header("authorization", "Bearer " + token))
                .andExpect(status().isNotFound()).andReturn();
        assertTrue(mvcResult.getResponse().getErrorMessage().contains("Employee is not found"));
    }

    @Test
    void userCanUpdateEmployeePassword() throws Exception{
        String newPassword = "newpassword1234";
        employeeId = employee1.getDNI();
        MvcResult mvcResult = mockMvc.perform(patch("/api/hotel/employees/password/"+employeeId+"/"+newPassword).contentType(MediaType.APPLICATION_JSON).header("authorization", "Bearer " + token))
                .andExpect(status().isOk()).andReturn();
    }
    @Test
    void employeeToUpdatePasswordDoesNotExist() throws Exception{
        String newPassword = "newpassword1234";
        employeeId = "qwerqwerqwerqwer";
        MvcResult mvcResult = mockMvc.perform(patch("/api/hotel/employees/password/"+employeeId+"/"+newPassword).contentType(MediaType.APPLICATION_JSON).header("authorization", "Bearer " + token))
                .andExpect(status().isNotFound()).andReturn();
        assertTrue(mvcResult.getResponse().getErrorMessage().contains("Employee qwerqwerqwerqwer is not found"));
    }

    @Test
    void userCanDeleteEmployee() throws Exception{
        employeeId = employee1.getDNI();
        mockMvc.perform(delete("/api/hotel/employees/"+employeeId).contentType(MediaType.APPLICATION_JSON).header("authorization", "Bearer " + token))
                .andExpect(status().isOk()).andReturn();
        assertFalse(employeeRepository.findByDNI(employeeId).isPresent());
    }

    @Test
    void employeeToDeleteDoesNotExist() throws Exception{

        MvcResult mvcResult = mockMvc.perform(delete("/api/hotel/employees/qwerqwerqwerqwer").contentType(MediaType.APPLICATION_JSON).header("authorization", "Bearer " + token))
                .andExpect(status().isNotFound()).andReturn();
        assertTrue(mvcResult.getResponse().getErrorMessage().contains("Employee qwerqwerqwerqwer is not found"));
    }
}
