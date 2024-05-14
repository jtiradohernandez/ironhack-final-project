package com.example.ironproject.service.Bookings;

import com.example.ironproject.DTO.Booking.FacilityBookingDTO;
import com.example.ironproject.model.Booking.FacilityBooking;
import com.example.ironproject.model.HotelStructure.Bedroom;
import com.example.ironproject.model.HotelStructure.Facility;
import com.example.ironproject.repository.Booking.FacilityBookingRepository;
import com.example.ironproject.repository.HotelStructure.FacilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class FacilityBookingService {
    @Autowired
    FacilityBookingRepository facilityBookingRepository;

    @Autowired
    FacilityRepository facilityRepository;

    public List<FacilityBooking> getAllFacilityBookings() {
        return facilityBookingRepository.findAll();
    }

    public Optional<FacilityBooking> getFacilityBookingById(int facilityBookingId) {
        return facilityBookingRepository.findFacilityBookingByBookingId(facilityBookingId);
    }

    public Optional<FacilityBooking> getAllFacilityBookingsOfHotel(int hotelId) {
        return facilityBookingRepository.findFacilityBookingByHotelId(hotelId);
    }

    public FacilityBooking addFacilityBooking(FacilityBooking facilityBooking) {
        if(checkAvailability(facilityBooking.getRoomBooked(),facilityBooking.getSlot(),facilityBooking.getSlot())){
            return facilityBookingRepository.save(facilityBooking);
        }else{
            return null;
        }
    }

    public List<Facility> getAvailableFacilities(int hotelId, Date initialDate, Date finishDate){
        List<Integer> unavailableFacilityId = facilityBookingRepository.findUnavailableFacilities(hotelId,initialDate, finishDate);
        List<Facility> allFacilities = facilityRepository.findAll();
        for(int i = 0; i < unavailableFacilityId.size();i++) {
            for (int j = 0; j < allFacilities.size();j++)
                if (unavailableFacilityId.get(i) == allFacilities.get(j).getRoomId()){
                    allFacilities.remove(j);
                }
        }
        return allFacilities;
    }

    public void deleteFacility(int facilityBookingId) {
        FacilityBooking booking = facilityBookingRepository.findFacilityBookingByBookingId(facilityBookingId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reservation " + facilityBookingId +" is not found"));
        booking.setClientOfBooking(null);
        booking.setWorkerAssigned(null);
        booking.setRoomBooked(null);
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

    private boolean checkAvailability(Facility facility, Date initialDate, Date finishDate){
        Integer desiredBedroomId = facilityBookingRepository.checkAvailability(facility.getRoomId(), initialDate, finishDate);
        if (desiredBedroomId == null){
            return true;
        }else {
            return false;
        }
    }
}
