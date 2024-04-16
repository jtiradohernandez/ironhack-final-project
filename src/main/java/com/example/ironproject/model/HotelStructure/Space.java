package com.example.ironproject.model.HotelStructure;


import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.util.ArrayList;
import java.util.Date;

@Getter
@Setter
@Entity
@Inheritance(strategy= InheritanceType.TABLE_PER_CLASS)
public class Space extends Room{
    @NotEmpty(message = "Space must have a name")
    String name;
    Boolean canBeBooked;
    @NotEmpty(message = "Room must have a capacity")
    @Positive
    int capacity;
    @NotEmpty(message = "Room must have an opening hours information")
    ArrayList<Date> openingHours;

    public Space(int hotelId, int floor, Boolean canBeBooked, int capacity, ArrayList<Date> openingHours) {
        super(hotelId, floor);
        this.canBeBooked = canBeBooked;
        this.capacity = capacity;
        this.openingHours = openingHours;
    }
}
