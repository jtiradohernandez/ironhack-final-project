package com.example.ironproject.repository.Booking;

import com.example.ironproject.model.Booking.FacilityBooking;
import com.example.ironproject.model.HotelStructure.Bedroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface FacilityBookingRepository extends JpaRepository<FacilityBooking, String> {
    Optional<FacilityBooking> findFacilityBookingByBookingId(int facilityBookingId);


    @Query(value = "SELECT * FROM facility_booking INNER JOIN bookings ON facility_booking.id = bookings.booking_id INNER JOIN room ON facility_booking.facility_booked = room.room_id WHERE room.room_of_hotel = :hotelId", nativeQuery=true)
    Optional<FacilityBooking> findFacilityBookingByHotelId(int hotelId);
}
