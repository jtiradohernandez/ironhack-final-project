package com.example.ironproject.controller.Booking;


import com.example.ironproject.model.Booking.BedroomBookings;
import com.example.ironproject.model.Booking.FacilityBooking;
import com.example.ironproject.model.People.Client;
import com.example.ironproject.service.Bookings.FacilityBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class FacilityBookingsController {

    @Autowired
    FacilityBookingService facilityBookingService;

    @GetMapping("/hotel/facilities/bookings")
    @ResponseStatus(HttpStatus.OK)
    public List<FacilityBooking> getFacilityBookings() {
        return facilityBookingService.getAllFacilityBookings();
    }

    @GetMapping("/hotel/facilities/bookings/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<FacilityBooking> getFacilityBookingById(@PathVariable(name="id") int facilityBookingId) {
        return facilityBookingService.getFacilityBookingById(facilityBookingId);
    }

    @DeleteMapping("/hotel/facilities/bookings/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteFacilityBooking(@PathVariable(name="id") int facilityBookingId){
        facilityBookingService.deleteFacility(facilityBookingId);
    }
}
