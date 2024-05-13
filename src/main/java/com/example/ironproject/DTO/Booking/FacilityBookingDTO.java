package com.example.ironproject.DTO.Booking;

import com.example.ironproject.enumeration.Service;
import com.example.ironproject.model.HotelStructure.Facility;
import com.example.ironproject.model.People.Employee;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.Future;
import java.util.Date;

@Data
public class FacilityBookingDTO extends BookingsDTO {
    private Employee workerAssigned;
    private Service service;
    @Future
    @JsonFormat(pattern = "YYYY-MM-dd-HH")
    private Date slot;
    private Facility roomBooked;
}
