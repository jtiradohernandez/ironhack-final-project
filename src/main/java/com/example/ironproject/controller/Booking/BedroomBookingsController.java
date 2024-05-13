package com.example.ironproject.controller.Booking;


import com.example.ironproject.DTO.Booking.BedroomBookingDTO;
import com.example.ironproject.model.Booking.BedroomBookings;
import com.example.ironproject.model.HotelStructure.Bedroom;
import com.example.ironproject.service.Bookings.BedroomBookingsService;
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
public class BedroomBookingsController {

    @Autowired
    BedroomBookingsService bedroomBookingsService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping("/bedrooms/bookings")
    @ResponseStatus(HttpStatus.OK)
    public List<BedroomBookings> getBedroomBookings() {
        return bedroomBookingsService.getAllBedroomBookings();
    }

    @GetMapping("/bedrooms/bookings/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<BedroomBookings> getBedroomBookingById(@PathVariable(name="id") int bedroomBookingId) {
        return bedroomBookingsService.getBedroomBookingById(bedroomBookingId);
    }

    @GetMapping("/{id}/bedrooms/bookings")
    @ResponseStatus(HttpStatus.OK)
    public BedroomBookings getBedroomBookingByHotelId(@PathVariable(name="id") int hotelId) {
        return bedroomBookingsService.getAllBedroomBookingsOfHotel(hotelId);
    }

    @GetMapping("/{id}/bedrooms/bookings/availability/{arrivalDate}/{departureDate}")
    @ResponseStatus(HttpStatus.OK)
    public List<Bedroom> getAvailableBedrooms(@PathVariable(name="id") int hotelId,@PathVariable(name="arrivalDate")@DateTimeFormat(pattern="yyyy-MM-dd") Date arrivalDate, @PathVariable(name="departureDate")@DateTimeFormat(pattern="yyyy-MM-dd") Date departureDate) {
        return bedroomBookingsService.getAvailableBedrooms(hotelId,arrivalDate,departureDate);
    }

    @PostMapping("/bedrooms/bookings")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> addBedroomBooking(@RequestBody @Valid BedroomBookings bedroomBooking) throws JsonProcessingException {
        BedroomBookings bedroomBookingCreated = bedroomBookingsService.addBedroomBooking(bedroomBooking);
        if(bedroomBookingCreated == null){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("The bedroom is unavailable for the date selected");
        }else{
            return ResponseEntity.status(HttpStatus.CREATED).body(objectMapper.writeValueAsString(bedroomBookingCreated));
        }
    }

    @PatchMapping("/bedrooms/bookings/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BedroomBookings updateBedroomBooking(@PathVariable(name="id") int bookingId, @RequestBody @Valid BedroomBookingDTO bedroomBookingDTO) {
        return bedroomBookingsService.updateBedroomBooking(bookingId, bedroomBookingDTO);
    }

    @DeleteMapping("/bedrooms/bookings/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteBedroomBooking(@PathVariable(name="id") int bedroomBookingId){
        bedroomBookingsService.deleteBedroomBooking(bedroomBookingId);
    }
}
