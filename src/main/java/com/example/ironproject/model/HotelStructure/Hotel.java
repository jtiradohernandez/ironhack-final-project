package com.example.ironproject.model.HotelStructure;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int hotelId;
    @NotEmpty(message = "name cannot be empty")
    private String name;
    @NotEmpty(message = "address cannot be empty")
    private String address;
    @NotEmpty(message = "region cannot be empty")
    private String Region;
    @NotEmpty(message = "planet cannot be empty")
    private String Planet;
    @NotEmpty(message = "capacity cannot be empty")
    @Positive
    private int capacity;

    public Hotel(String name, String address, String region, String planet, int capacity) {
        this.name = name;
        this.address = address;
        Region = region;
        Planet = planet;
        this.capacity = capacity;
    }
}
