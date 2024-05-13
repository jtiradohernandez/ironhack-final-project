package com.example.ironproject.DTO.Booking;

import com.example.ironproject.model.HotelStructure.Bedroom;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.Future;
import java.util.Date;

@Data
public class BedroomBookingDTO extends BookingsDTO{
    @Future
    @JsonFormat(pattern = "YYYY-MM-dd")
    private Date arrivalDate;
    @Future
    @JsonFormat(pattern = "YYYY-MM-dd")
    private Date departureDate;
    private Bedroom roomBooked;
}
