package com.example.ironproject.DTO.HotelStructure;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

@Data
abstract class RoomDTO {
    private int roomId;
    @NotEmpty(message = "Room must be assigned to an hotel")
    @Positive(message = "ID be negative or zero")
    private int hotelId;
    @NotEmpty(message = "Facility must have a capacity")
    @Positive(message = "Capacity cannot be negative or zero")
    private Integer capacity;
    @NotEmpty(message = "Room must be in any floor")
    private Integer floor;
}
