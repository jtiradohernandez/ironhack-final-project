package com.example.ironproject.model.HotelStructure;

import jakarta.persistence.*;
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
    @Positive(message = "ID be negative or zero")
    @ManyToOne
    @JoinColumn(name="hotel_id")
    int hotelId;
    @NotEmpty(message = "Facility must have a capacity")
    @Positive(message = "Capacity cannot be negative or zero")
    int capacity;
    @NotEmpty(message = "Room must be in any floor")
    int floor;

    public Room(int hotelId, int floor, int capacity) {
        this.hotelId = hotelId;
        this.floor = floor;
        this.capacity = capacity;
    }
}
