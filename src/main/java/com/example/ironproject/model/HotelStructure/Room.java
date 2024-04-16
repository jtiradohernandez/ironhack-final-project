package com.example.ironproject.model.HotelStructure;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

@Getter
@Setter
@Entity
abstract class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int roomId;
    @NotEmpty(message = "Room must be assigned to an hotel")
    @Positive
    int hotelId;
    @NotEmpty(message = "Room must be in any floor")
    int floor;

    public Room(int hotelId, int floor) {
        this.hotelId = hotelId;
        this.floor = floor;
    }
}
