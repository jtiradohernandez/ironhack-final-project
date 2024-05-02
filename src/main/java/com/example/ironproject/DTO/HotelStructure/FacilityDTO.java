package com.example.ironproject.DTO.HotelStructure;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.Date;

@Data
public class FacilityDTO extends RoomDTO {
    private String name;
    private Boolean canBeBooked;
    private ArrayList<Date> openingHours;
}
