package com.example.ironproject.DTO.HotelStructure;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Optional;

@Data
public class HotelDTO {
    @NotNull
    int hotelId;
    @NotEmpty(message = "name cannot be empty")
    String name;
    @NotEmpty(message = "address cannot be empty")
    String address;
    @NotEmpty(message = "region cannot be empty")
    String Region;
    @NotEmpty(message = "planet cannot be empty")
    String Planet;
    @NotEmpty(message = "capacity cannot be empty")
    @Positive
    Integer capacity;
}
