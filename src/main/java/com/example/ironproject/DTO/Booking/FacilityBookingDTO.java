package com.example.ironproject.DTO.Booking;

import com.example.ironproject.enumeration.Service;
import com.example.ironproject.model.HotelStructure.Facility;
import com.example.ironproject.model.People.Employee;
import lombok.Data;

import javax.validation.constraints.Future;
import java.util.Date;

@Data
public class FacilityBookingDTO extends BookingsDTO {
    private Employee workerAssigned;
    private Service service;
    @Future
    private Date slot;
    private Facility roomBooked;
}
