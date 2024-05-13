package com.example.ironproject.repository.Booking;

import com.example.ironproject.model.Booking.FacilityBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface FacilityBookingRepository extends JpaRepository<FacilityBooking, String> {
    Optional<FacilityBooking> findFacilityBookingByBookingId(int facilityBookingId);


    @Query(value = "SELECT * FROM facility_booking INNER JOIN bookings ON facility_booking.id = bookings.booking_id INNER JOIN room ON facility_booking.facility_booked = room.room_id WHERE room.room_of_hotel = :hotelId", nativeQuery=true)
    Optional<FacilityBooking> findFacilityBookingByHotelId(int hotelId);

    @Query(value = "SELECT facility.id FROM facility_bookings " +
            "INNER JOIN facilty ON facility.id = facility_booking.facility_booked " +
            "INNER JOIN room ON facility.id = room.room_id " +
            "WHERE room.room_of_hotel = :hotelId " +
            "AND facility_booking.slot <= :finishDate " +
            "AND facility_booking.slot >= :initialDate", nativeQuery=true)
    List<Integer> findUnavailableFacilities(int hotelId, Date initialDate, Date finishDate);

    @Query(value = "SELECT facility_booking.id FROM facility_booking " +
            "INNER JOIN facility ON facility.id = facility_booking.facility_booked " +
            "WHERE facility.id = :facilityId " +
            "AND facility_booking.slot <= :finishDate " +
            "AND facility_booking.slot >= :initialDate", nativeQuery=true)
    Integer checkAvailability(int facilityId, Date initialDate, Date finishDate);
}
