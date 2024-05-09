package com.example.ironproject.DTO.HotelStructure;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

@Data
public class BedroomDTO extends RoomDTO {
    @Positive(message = "Room number cannot be negative or zero")
    private Integer roomNumber;
}
