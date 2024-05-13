package com.example.ironproject.controller.Booking;


import com.example.ironproject.DTO.Booking.FacilityBookingDTO;
import com.example.ironproject.model.Booking.FacilityBooking;
import com.example.ironproject.model.HotelStructure.Facility;
import com.example.ironproject.service.Bookings.FacilityBookingService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/hotel")
public class FacilityBookingsController {

    @Autowired
    FacilityBookingService facilityBookingService;

    private final ObjectMapper objectMapper = new ObjectMapper();

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

    @GetMapping("/{id}/facilities/bookings/availability/{initialDate}/{finishDate}")
    @ResponseStatus(HttpStatus.OK)
    public List<Facility> getAvailableBedrooms(@PathVariable(name="id") int hotelId, @PathVariable(name="initialDate")@DateTimeFormat(pattern="yyyy-MM-dd-HH") Date initialDate, @PathVariable(name="finishDate")@DateTimeFormat(pattern="yyyy-MM-dd-HH") Date finishDate) {
        return facilityBookingService.getAvailableFacilities(hotelId,initialDate,finishDate);
    }

    @PostMapping("/facilities/bookings")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> addFacilityBooking(@RequestBody @Valid FacilityBooking facilityBooking) throws JsonProcessingException {
        FacilityBooking facilityBookingCreated = facilityBookingService.addFacilityBooking(facilityBooking);
        if(facilityBookingCreated == null){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("The facility is unavailable for the slot selected");
        }else{
            return ResponseEntity.status(HttpStatus.CREATED).body(objectMapper.writeValueAsString(facilityBookingCreated));
        }
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
