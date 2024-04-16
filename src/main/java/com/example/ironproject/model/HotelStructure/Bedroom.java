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
    @NotEmpty(message = "This bedroom need beds")
    @Positive
    int bedAmount;
    @NotEmpty(message = "The bedroom must be assigned to a room number")
    @Positive
    int roomNumber;
    Boolean availability;

    public Bedroom(int hotelId, int floor, int bedAmount, int roomNumber) {
        super(hotelId, floor);
        this.bedAmount = bedAmount;
        this.roomNumber = roomNumber;
        this.availability = true;
    }
}
