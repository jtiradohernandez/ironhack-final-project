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
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roomId;
    @NotEmpty(message = "Room must be assigned to an hotel")
    @Positive(message = "ID be negative or zero")
    @ManyToOne
    @JoinColumn(name="room_of_hotel")
    private Hotel roomOfHotel;
    @NotEmpty(message = "Facility must have a capacity")
    @Positive(message = "Capacity cannot be negative or zero")
    private int capacity;
    @NotEmpty(message = "Room must be in any floor")
    private int floor;

    public Room(Hotel roomOfHotel, int floor, int capacity) {
        this.roomOfHotel = roomOfHotel;
        this.floor = floor;
        this.capacity = capacity;
    }
}
