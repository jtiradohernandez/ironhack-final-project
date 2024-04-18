package com.example.ironproject.DTO.HotelStructure;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

@Data
public class BedroomDTO {

    int roomId;
    @NotEmpty(message = "The bedroom must be assigned to a room number")
    @Positive(message = "Room number cannot be negative or zero")
    int roomNumber;
    Boolean availability;
}
