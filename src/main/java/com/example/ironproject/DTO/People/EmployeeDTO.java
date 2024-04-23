package com.example.ironproject.DTO.People;

import com.example.ironproject.enumeration.Jobs;
import com.example.ironproject.model.HotelStructure.Hotel;
import com.example.ironproject.model.Security.Role;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class EmployeeDTO extends PersonDTO{
    @NotEmpty(message = "employee must have a job assigned")
    private Jobs job;
    @ManyToOne
    @JoinColumn(name="role_employee")
    private Role roleEmployee;
    private String username;
    private String password;
    @NotEmpty(message = "employee must be assigned to an hotel")
    @ManyToOne
    @JoinColumn(name="hotel_assigned")
    private Hotel hotelAssigned;
}
