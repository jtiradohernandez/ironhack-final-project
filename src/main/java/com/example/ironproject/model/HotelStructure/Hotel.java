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
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int hotelId;
    String name;
    String direction;
    String Region;
    String Planet;
    int rating;
    int capacity;

    public Hotel(String name, String direction, String region, String planet, int rating, int capacity) {
        this.name = name;
        this.direction = direction;
        Region = region;
        Planet = planet;
        this.rating = rating;
        this.capacity = capacity;
    }
}
