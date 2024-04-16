package com.example.ironproject.repository.HotelStructure;

import com.example.ironproject.model.HotelStructure.Bedroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BedroomRepository extends JpaRepository<Bedroom, String> {
    List<Bedroom> findBedroomByHotelId(int id);

    Optional<Bedroom> findByRoomId(int id);
}