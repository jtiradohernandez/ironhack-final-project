package com.example.ironproject.repository.HotelStructure;

import com.example.ironproject.model.HotelStructure.Bedroom;
import com.example.ironproject.model.HotelStructure.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BedroomRepository extends JpaRepository<Bedroom, String> {
    List<Bedroom> findBedroomByHotel(Optional<Hotel> hotel);

    Optional<Bedroom> findByRoomId(int id);

}
