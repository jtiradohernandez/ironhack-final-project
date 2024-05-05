package com.example.ironproject.service.Bookings;

import com.example.ironproject.DTO.Booking.FacilityBookingDTO;
import com.example.ironproject.model.Booking.FacilityBooking;
import com.example.ironproject.repository.Booking.FacilityBookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
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

    public Optional<FacilityBooking> getAllFacilityBookingsOfHotel(int hotelId) {
        return null;
        //return facilityBookingRepository.findFacilityBookingByHotelId(hotelId);
    }

    public FacilityBooking addFacilityBooking(FacilityBooking facilityBooking) {
        return facilityBookingRepository.save(facilityBooking);
    }

    public void deleteFacility(int facilityBookingId) {
        FacilityBooking booking = facilityBookingRepository.findFacilityBookingByBookingId(facilityBookingId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reservation " + facilityBookingId +" is not found"));
        facilityBookingRepository.delete(booking);
    }

    public FacilityBooking updateFacilityBooking(int bookingId, FacilityBookingDTO newBooking) {
        FacilityBooking booking = facilityBookingRepository.findFacilityBookingByBookingId(bookingId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reservation " + bookingId +" is not found"));
        if(newBooking.getRoomBooked() != null){
            booking.setRoomBooked(newBooking.getRoomBooked());
        }
        if(newBooking.getClientOfBooking() != null){
            booking.setClientOfBooking(newBooking.getClientOfBooking());
        }
        if(newBooking.getWorkerAssigned() != null){
            booking.setWorkerAssigned(newBooking.getWorkerAssigned());
        }
        if(newBooking.getService() != null){
            booking.setService(newBooking.getService());
        }
        if(newBooking.getSlot() != null){
            booking.setSlot(newBooking.getSlot());
        }
        return facilityBookingRepository.save(booking);
    }
}
