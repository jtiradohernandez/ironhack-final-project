package com.example.ironproject.model.HotelStructure;


import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.util.ArrayList;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Inheritance(strategy= InheritanceType.TABLE_PER_CLASS)
public class Facility extends Room{
    @NotEmpty(message = "Facility must have a name")
    private String name;
    Boolean canBeBooked;
    @NotEmpty(message = "Facility must have an opening hours information")
    private ArrayList<Date> openingHours;

    public Facility(Hotel hotel,String name, int floor, Boolean canBeBooked, int capacity/*, ArrayList<Date> openingHours*/) {
        super(hotel, floor, capacity);
        this.name = name;
        this.canBeBooked = canBeBooked;
        //this.openingHours = openingHours;
    }
}
