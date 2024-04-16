package com.example.ironproject.model.HotelStructure;


import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Bedroom extends Room {
    int bedAmount;
    int roomNumber;
    Boolean availability;

    public Bedroom(int hotelId, int floor, int bedAmount, int roomNumber, Boolean availability) {
        super(hotelId, floor);
        this.bedAmount = bedAmount;
        this.roomNumber = roomNumber;
        this.availability = availability;
    }
}
