package com.example.ironproject.DTO.Booking;

import com.example.ironproject.model.Booking.Service;
import lombok.Data;

import javax.validation.constraints.Future;
import java.util.Date;

@Data
public class FacilityBookingDTO extends BookingsDTO {
    private Integer workerId;
    private Service service;
    @Future
    private Date slot;
}
