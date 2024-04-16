package com.example.ironproject.repository.HotelStructure;

import com.example.ironproject.model.HotelStructure.Space;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SpaceRepository extends JpaRepository<Space, String> {
    List<Space> findSpacesByHotelId(int hotelId);

    Optional<Space> findByRoomId(int id);
}
