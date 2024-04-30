package com.example.ironproject.service.Bookings;

import com.example.ironproject.model.Booking.FacilityBooking;
import com.example.ironproject.model.HotelStructure.Facility;
import com.example.ironproject.repository.Booking.FacilityBookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class FacilityBookingService {
    @Autowired
    FacilityBookingRepository facilityBookingRepository;
    public List<FacilityBooking> getAllFacilityBookings() {
        return facilityBookingRepository.findAll();
    }

    public Optional<FacilityBooking> getFacilityBookingById(int facilityBookingId) {
        return facilityBookingRepository.findFacilityBookingByBookingId(facilityBookingId);
    }

    public void deleteFacility(int facilityBookingId) {
        FacilityBooking booking = facilityBookingRepository.findFacilityBookingByBookingId(facilityBookingId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reservation " + facilityBookingId +" is not found"));
        facilityBookingRepository.delete(booking);
    }
}
