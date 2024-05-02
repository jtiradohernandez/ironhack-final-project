package com.example.ironproject.DTO.HotelStructure;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

@Data
abstract class RoomDTO {
    private int roomId;
    @Positive(message = "ID be negative or zero")
    private int hotelId;
    @Positive(message = "Capacity cannot be negative or zero")
    private Integer capacity;
    private Integer floor;
}
