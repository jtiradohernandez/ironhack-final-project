package com.example.ironproject.model.HotelStructure;


import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@PrimaryKeyJoinColumn(name="id")
public class Facility extends Room{
    @NotEmpty(message = "Facility must have a name")
    private String name;
    Boolean canBeBooked;


    public Facility(Hotel hotel,String name, int floor, Boolean canBeBooked, int capacity) {
        super(hotel, floor, capacity);
        this.name = name;
        this.canBeBooked = canBeBooked;
    }
}
