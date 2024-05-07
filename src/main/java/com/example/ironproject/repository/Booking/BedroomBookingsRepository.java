package com.example.ironproject.repository.Booking;

import com.example.ironproject.model.Booking.BedroomBookings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface BedroomBookingsRepository extends JpaRepository<BedroomBookings, String> {
    Optional<BedroomBookings> findBedroomBookingsByBookingId(int bedroomBookingId);

    //@Query("SELECT * FROM bedroomBooking INNER JOIN Bedroom ON bedroomBooking.roomBooked = Bedroom.roomId WHERE Bedroom.roomOfHotel = :hotelId")
    @Query(value = "SELECT * FROM bedroom_bookings INNER JOIN bookings ON bedroom_bookings.id = bookings.booking_id INNER JOIN room ON bookings.room_booked = room.room_id WHERE room.room_of_hotel = :hotelId", nativeQuery=true)
    BedroomBookings findBedroomBookingByHotelId(int hotelId);
}