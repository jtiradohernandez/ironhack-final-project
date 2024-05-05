package com.example.ironproject.repository.Booking;

import com.example.ironproject.model.Booking.BedroomBookings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface BedroomBookingsRepository extends JpaRepository<BedroomBookings, String> {
    Optional<BedroomBookings> findBedroomBookingsByBookingId(int bedroomBookingId);

    //@Query("SELECT * FROM bedroomBooking")
    //BedroomBookings findBedroomBookingByHotelId(int hotelId); //TODO crear una busqueda que relacione habitaciones con hotel
}
