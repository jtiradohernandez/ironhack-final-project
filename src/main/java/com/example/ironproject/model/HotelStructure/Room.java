package com.example.ironproject.model.HotelStructure;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
abstract class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int roomId;
    int hotelId;
    int floor;

    public Room(int hotelId, int floor) {
        this.hotelId = hotelId;
        this.floor = floor;
    }
}
