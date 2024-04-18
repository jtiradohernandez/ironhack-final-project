package com.example.ironproject.model.HotelStructure;


import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.util.ArrayList;
import java.util.Date;

@Getter
@Setter
@Entity
@Inheritance(strategy= InheritanceType.TABLE_PER_CLASS)
public class Facility extends Room{
    @NotEmpty(message = "Facility must have a name")
    String name;
    Boolean canBeBooked;
    @NotEmpty(message = "Facility must have an opening hours information")
    ArrayList<Date> openingHours;

    public Facility(int hotelId, int floor, Boolean canBeBooked, int capacity, ArrayList<Date> openingHours) {
        super(hotelId, floor, capacity);
        this.canBeBooked = canBeBooked;
        this.openingHours = openingHours;
    }
}
