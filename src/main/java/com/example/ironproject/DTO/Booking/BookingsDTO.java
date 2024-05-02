package com.example.ironproject.DTO.Booking;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class BookingsDTO {
    private Integer roomId;
    private Integer clientId;
}
