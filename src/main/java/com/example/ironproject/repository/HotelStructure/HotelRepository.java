package com.example.ironproject.repository.HotelStructure;

import com.example.ironproject.model.HotelStructure.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, String> {
    Optional<Hotel> findByHotelId(int id);
}
