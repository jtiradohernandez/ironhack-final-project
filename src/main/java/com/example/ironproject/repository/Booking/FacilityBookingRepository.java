package com.example.ironproject.repository.Booking;

import com.example.ironproject.model.Booking.FacilityBooking;
import com.example.ironproject.model.HotelStructure.Bedroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface FacilityBookingRepository extends JpaRepository<FacilityBooking, String> {
    Optional<FacilityBooking> findFacilityBookingByBookingId(int facilityBookingId);


    //@Query("SELECT * FROM facilityBooking ")
    //Optional<FacilityBooking> findFacilityBookingByHotelId(int hotelId); //TODO comprobar que hace bien la comprobacion
}