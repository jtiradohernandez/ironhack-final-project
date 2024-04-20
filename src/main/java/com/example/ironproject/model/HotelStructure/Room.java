package com.example.ironproject.model.HotelStructure;

import jakarta.persistence.*;
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
abstract class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int roomId;
    @NotEmpty(message = "Room must be assigned to an hotel")
    @Positive(message = "ID be negative or zero")
    @ManyToOne
    @JoinColumn(name="hotel_id")
    Hotel hotel;
    @NotEmpty(message = "Facility must have a capacity")
    @Positive(message = "Capacity cannot be negative or zero")
    int capacity;
    @NotEmpty(message = "Room must be in any floor")
    int floor;

    public Room(Hotel hotel, int floor, int capacity) {
        this.hotel = hotel;
        this.floor = floor;
        this.capacity = capacity;
    }
}
