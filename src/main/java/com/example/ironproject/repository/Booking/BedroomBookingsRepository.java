package com.example.ironproject.repository.Booking;

import com.example.ironproject.model.Booking.BedroomBookings;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BedroomBookingsRepository extends JpaRepository<BedroomBookings, String> {
    Optional<BedroomBookings> findBedroomBookingsByBookingId(int bedroomBookingId);
}
