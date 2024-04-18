package com.example.ironproject.model.HotelStructure;


import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

@Getter
@Setter
@Entity
public class Bedroom extends Room {
    @NotEmpty(message = "The bedroom must be assigned to a room number")
    @Positive(message = "Room number cannot be negative or zero")
    int roomNumber;
    Boolean availability;

    public Bedroom(int hotelId, int floor, int capacity, int roomNumber) {
        super(hotelId, floor, capacity);
        this.roomNumber = roomNumber;
        this.availability = true;
    }
}
