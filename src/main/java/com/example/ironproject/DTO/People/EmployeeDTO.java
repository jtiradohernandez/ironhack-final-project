package com.example.ironproject.DTO.People;

import com.example.ironproject.enumeration.Jobs;
import com.example.ironproject.model.HotelStructure.Hotel;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class EmployeeDTO extends PersonDTO{
    private Jobs job;
    @ManyToOne
    @JoinColumn(name="hotel_assigned")
    private Hotel hotelAssigned;
}
