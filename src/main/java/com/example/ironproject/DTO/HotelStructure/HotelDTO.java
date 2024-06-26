package com.example.ironproject.DTO.HotelStructure;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class HotelDTO {
    @NotNull
    private int hotelId;
    private String name;
    private String address;
    private String Region;
    private String Planet;
    @Positive
    private Integer capacity;
}
