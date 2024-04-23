package com.example.ironproject.model.HotelStructure;


import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Bedroom extends Room {
    @NotEmpty(message = "The bedroom must be assigned to a room number")
    @Positive(message = "Room number cannot be negative or zero")
    private int roomNumber;
    private Boolean availability;

    public Bedroom(Hotel hotel, int floor, int capacity, int roomNumber) {
        super(hotel, floor, capacity);
        this.roomNumber = roomNumber;
        this.availability = true;
    }
}
