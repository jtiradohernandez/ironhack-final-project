package com.example.ironproject.service.HotelStructure;

import com.example.ironproject.model.HotelStructure.Bedroom;
import com.example.ironproject.model.HotelStructure.Space;
import com.example.ironproject.repository.HotelStructure.SpaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SpaceService {

    @Autowired
    SpaceRepository spaceRepository;
    public List<Space> getAllSpaces(){
        return spaceRepository.findAll();
    }

    public List<Space> getAllSpacesOfHotel(int hotelId){
        return spaceRepository.findSpacesByHotelId(hotelId);
    }

    public Optional<Space> getSpaceById(int id){
        return spaceRepository.findByRoomId(id);
    }
}
