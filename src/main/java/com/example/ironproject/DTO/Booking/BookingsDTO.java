package com.example.ironproject.DTO.Booking;

import com.example.ironproject.model.HotelStructure.Bedroom;
import com.example.ironproject.model.People.Client;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class BookingsDTO {
    private Client clientOfBooking;
}
