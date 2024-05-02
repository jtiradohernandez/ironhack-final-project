package com.example.ironproject.DTO.Booking;

import lombok.Data;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Data
public class BedroomBookingDTO extends BookingsDTO{
    @Future
    private Date arrivalDate;
    @Future
    private Date departureDate;
}
