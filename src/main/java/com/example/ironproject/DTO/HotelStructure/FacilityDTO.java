package com.example.ironproject.DTO.HotelStructure;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.Date;

@Data
public class FacilityDTO extends RoomDTO {
    @NotEmpty(message = "Facility must have a name")
    private String name;
    private Boolean canBeBooked;
    @NotEmpty(message = "Facility must have an opening hours information")
    private ArrayList<Date> openingHours;
}
