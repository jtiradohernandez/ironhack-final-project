package com.example.ironproject.repository.HotelStructure;

import com.example.ironproject.model.HotelStructure.Facility;
import com.example.ironproject.model.HotelStructure.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FacilityRepository extends JpaRepository<Facility, String> {
    List<Facility> findFacilitiesByRoomOfHotel(Optional<Hotel> hotel);

    Optional<Facility> findByRoomId(int id);
}
