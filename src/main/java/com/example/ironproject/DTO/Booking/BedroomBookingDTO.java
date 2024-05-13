package com.example.ironproject.DTO.Booking;

import com.example.ironproject.model.HotelStructure.Bedroom;
import lombok.Data;

import javax.validation.constraints.Future;
import java.util.Date;

@Data
public class BedroomBookingDTO extends BookingsDTO{
    @Future
    private Date arrivalDate;
    @Future
    private Date departureDate;
    private Bedroom roomBooked;
}
