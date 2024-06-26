package com.example.ironproject.service.Bookings;

import com.example.ironproject.DTO.Booking.BedroomBookingDTO;
import com.example.ironproject.model.Booking.BedroomBookings;
import com.example.ironproject.model.HotelStructure.Bedroom;
import com.example.ironproject.repository.Booking.BedroomBookingsRepository;
import com.example.ironproject.repository.HotelStructure.BedroomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BedroomBookingsService {

    @Autowired
    BedroomBookingsRepository bedroomBookingsRepository;
    @Autowired
    BedroomRepository bedroomRepository;

    public List<BedroomBookings> getAllBedroomBookings() {
        return bedroomBookingsRepository.findAll();
    }

    public Optional<BedroomBookings> getBedroomBookingById(int bedroomBookingId) {
        return bedroomBookingsRepository.findBedroomBookingsByBookingId(bedroomBookingId);
    }

    public BedroomBookings getAllBedroomBookingsOfHotel(int hotelId) {
        return bedroomBookingsRepository.findBedroomBookingByHotelId(hotelId);
    }

    public List<Bedroom> getAvailableBedrooms(int hotelId, Date arrivalDate, Date departureDate){
        List<Integer> unavailableBedroomsId = bedroomBookingsRepository.findUnavailableBedrooms(hotelId,arrivalDate, departureDate);
        List<Bedroom> allBedrooms = bedroomRepository.findAll();
        for(int i = 0; i < unavailableBedroomsId.size();i++) {
            for (int j = 0; j < allBedrooms.size();j++)
                if (unavailableBedroomsId.get(i) == allBedrooms.get(j).getRoomId()){
                    allBedrooms.remove(j);
                }
        }
        return allBedrooms;
    }

    public BedroomBookings addBedroomBooking(BedroomBookings bedroomBooking) {
        if(checkAvailability(bedroomBooking.getRoomBooked(),bedroomBooking.getArrivalDate(),bedroomBooking.getDepartureDate())){
            return bedroomBookingsRepository.save(bedroomBooking);
        }else{
            return null;
        }
    }

    public void deleteBedroomBooking(int bedroomBookingId) {
        BedroomBookings booking = bedroomBookingsRepository.findBedroomBookingsByBookingId(bedroomBookingId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reservation " + bedroomBookingId +" is not found"));
        booking.setClientOfBooking(null);
        booking.setRoomBooked(null);
        bedroomBookingsRepository.delete(booking);
    }

    public BedroomBookings updateBedroomBooking(int bookingId, BedroomBookingDTO newBooking) {
        BedroomBookings booking = bedroomBookingsRepository.findBedroomBookingsByBookingId(bookingId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reservation " + bookingId +" is not found"));
        if(newBooking.getRoomBooked() != null){
            booking.setRoomBooked(newBooking.getRoomBooked());
        }
        if(newBooking.getClientOfBooking() != null){
            booking.setClientOfBooking(newBooking.getClientOfBooking());
        }
        if(newBooking.getArrivalDate() != null){
            booking.setArrivalDate(newBooking.getArrivalDate());
        }
        if(newBooking.getDepartureDate() != null){
            booking.setDepartureDate(newBooking.getDepartureDate());
        }
        return bedroomBookingsRepository.save(booking);
    }

    private boolean checkAvailability(Bedroom bedroom, Date arrivalDate, Date departureDate){
        Integer desiredBedroomId = bedroomBookingsRepository.checkAvailability(bedroom.getRoomId(), arrivalDate, departureDate);
        if (desiredBedroomId == null){
            return true;
        }else {
            return false;
        }
    }
}
