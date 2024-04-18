package com.example.ironproject.service.HotelStructure;

import com.example.ironproject.model.HotelStructure.Bedroom;
import com.example.ironproject.model.HotelStructure.Hotel;
import com.example.ironproject.repository.HotelStructure.BedroomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
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
        return bedroomRepository.findByBedroomId(id);
    }

    public List<Bedroom> addBedrooms(List<Bedroom> bedrooms) {
        List<Bedroom> bedroomsRepo = new ArrayList<Bedroom>();
        for(int i = 0; i <= bedrooms.size();i++){
            bedroomsRepo.add(bedroomRepository.save(bedrooms.get(i)));
        }
        return bedroomsRepo;
    }

    public void deleteBedrooms(List<int> bedroomIDs) {
        for(int i = 0; i <= bedroomIDs.size();i++){
            int id = bedroomIDs.get(i);
            bedroomRepository.delete(bedroomRepository.findByBedroomId(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Bedroom " + id +" not found")));
        }
    }
}
