package com.example.ironproject.repository.Booking;

import com.example.ironproject.model.Booking.BedroomBookings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface BedroomBookingsRepository extends JpaRepository<BedroomBookings, String> {
    Optional<BedroomBookings> findBedroomBookingsByBookingId(int bedroomBookingId);


    @Query(value = "SELECT * FROM bedroom_bookings INNER JOIN bookings ON bedroom_bookings.id = bookings.booking_id INNER JOIN room ON bedroom_bookings.bedroom_booked = room.room_id WHERE room.room_of_hotel = :hotelId", nativeQuery=true)
    BedroomBookings findBedroomBookingByHotelId(int hotelId);

    @Query(value = "SELECT bedroom.id FROM bedroom_bookings " +
            "INNER JOIN bedroom ON bedroom.id = bedroom_bookings.bedroom_booked " +
            "INNER JOIN room ON bedroom.id = room.room_id " +
            "WHERE room.room_of_hotel = :hotelId " +
            "AND bedroom_bookings.arrival_date < :departureDate " +
            "AND bedroom_bookings.departure_date > :arrivalDate", nativeQuery=true)
    List<Integer> findUnavailableBedrooms(int hotelId, Date arrivalDate, Date departureDate);

    @Query(value = "SELECT bedroom_bookings.id FROM bedroom_bookings " +
            "INNER JOIN bedroom ON bedroom.id = bedroom_bookings.bedroom_booked " +
            "WHERE bedroom.id = :bedroomId " +
            "AND bedroom_bookings.arrival_date <= :departureDate " +
            "AND bedroom_bookings.departure_date >= :arrivalDate", nativeQuery=true)
    Integer checkAvailability(int bedroomId, Date arrivalDate, Date departureDate);
}