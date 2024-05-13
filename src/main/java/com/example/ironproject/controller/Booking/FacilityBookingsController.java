package com.example.ironproject.controller.Booking;


import com.example.ironproject.DTO.Booking.FacilityBookingDTO;
import com.example.ironproject.model.Booking.FacilityBooking;
import com.example.ironproject.service.Bookings.FacilityBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/hotel")
public class FacilityBookingsController {

    @Autowired
    FacilityBookingService facilityBookingService;

    @GetMapping("/facilities/bookings")
    @ResponseStatus(HttpStatus.OK)
    public List<FacilityBooking> getFacilityBookings() {
        return facilityBookingService.getAllFacilityBookings();
    }

    @GetMapping("/facilities/bookings/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<FacilityBooking> getFacilityBookingById(@PathVariable(name="id") int facilityBookingId) {
        return facilityBookingService.getFacilityBookingById(facilityBookingId);
    }

    @GetMapping("/{id}/facilities/bookings")
    @ResponseStatus(HttpStatus.OK)
    public Optional<FacilityBooking> getFacilityBookingByHotelId(@PathVariable(name="id") int hotelId) {
        return facilityBookingService.getAllFacilityBookingsOfHotel(hotelId);
    }

    @PostMapping("/facilities/bookings")
    @ResponseStatus(HttpStatus.CREATED)
    public FacilityBooking addFacilityBooking(@RequestBody @Valid FacilityBooking facilityBooking) {
        return facilityBookingService.addFacilityBooking(facilityBooking);
    }

    @PatchMapping("/facilities/bookings/{id}")
    @ResponseStatus(HttpStatus.OK)
    public FacilityBooking updateFacilityBooking(@PathVariable(name="id") int bookingId, @RequestBody @Valid FacilityBookingDTO facilityBookingDTO) {
        return facilityBookingService.updateFacilityBooking(bookingId, facilityBookingDTO);
    }

    @DeleteMapping("/facilities/bookings/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteFacilityBooking(@PathVariable(name="id") int facilityBookingId){
        facilityBookingService.deleteFacility(facilityBookingId);
    }
}
