package com.example.ironproject.service.HotelStructure;

import com.example.ironproject.model.HotelStructure.Bedroom;
import com.example.ironproject.model.HotelStructure.Hotel;
import com.example.ironproject.repository.HotelStructure.BedroomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Service
public class BedroomService {

    @Autowired
    BedroomRepository bedroomRepository;

    public List<Bedroom> getAllBedrooms(){
        return bedroomRepository.findAll();
    }

    public List<Bedroom> getAllBedroomsOfHotel(int hotelId){
        return bedroomRepository.findBedroomByHotelId(hotelId);
    }

    public Optional<Bedroom> getBedroomById(int id){
        return bedroomRepository.findByRoomId(id);
    }
}
