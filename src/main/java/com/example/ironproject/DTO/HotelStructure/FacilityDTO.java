package com.example.ironproject.DTO.HotelStructure;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.Date;

@Data
public class FacilityDTO {
    int roomId;
    @NotEmpty(message = "Facility must have a name")
    String name;
    Boolean canBeBooked;
    @NotEmpty(message = "Facility must have an opening hours information")
    ArrayList<Date> openingHours;
}
