package com.example.ironproject.controller.Booking;


import com.example.ironproject.model.Booking.BedroomBookings;
import com.example.ironproject.model.Booking.FacilityBooking;
import com.example.ironproject.model.HotelStructure.Facility;
import com.example.ironproject.service.Bookings.BedroomBookingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class BedroomBookingsController {

    @Autowired
    BedroomBookingsService bedroomBookingsService;

    @GetMapping("/hotel/bedrooms/bookings")
    @ResponseStatus(HttpStatus.OK)
    public List<BedroomBookings> getBedroomBookings() {
        return bedroomBookingsService.getAllBedroomBookings();
    }

    @GetMapping("/hotel/bedrooms/bookings/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<BedroomBookings> getBedroomBookingById(@PathVariable(name="id") int bedroomBookingId) {
        return bedroomBookingsService.getBedroomBookingById(bedroomBookingId);
    }

    @DeleteMapping("/hotel/bedrooms/bookings/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteBedroomBooking(@PathVariable(name="id") int bedroomBookingId){
        bedroomBookingsService.deleteBedroomBooking(bedroomBookingId);
    }
}
