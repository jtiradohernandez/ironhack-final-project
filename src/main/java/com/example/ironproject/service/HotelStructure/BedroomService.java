package com.example.ironproject.service.HotelStructure;

import com.example.ironproject.DTO.HotelStructure.BedroomDTO;
import com.example.ironproject.model.HotelStructure.Bedroom;
import com.example.ironproject.model.HotelStructure.Facility;
import com.example.ironproject.repository.HotelStructure.BedroomRepository;
import com.example.ironproject.repository.HotelStructure.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BedroomService {

    @Autowired
    private BedroomRepository bedroomRepository;
    @Autowired
    private HotelRepository hotelRepository;

    public List<Bedroom> getAllBedrooms(){
        return bedroomRepository.findAll();
    }

    public List<Bedroom> getAllBedroomsOfHotel(int hotelId){
        return bedroomRepository.findBedroomByRoomOfHotel(hotelRepository.findByHotelId(hotelId));
    }

    public Optional<Bedroom> getBedroomById(int id){
        return bedroomRepository.findByRoomId(id);
    }

    public List<Bedroom> addBedrooms(List<Bedroom> bedrooms) {
        List<Bedroom> bedroomsRepo = new ArrayList<Bedroom>();
        for(int i = 0; i < bedrooms.size();i++){
            bedroomsRepo.add(bedroomRepository.save(bedrooms.get(i)));
        }
        return bedroomsRepo;
    }

    public void deleteBedrooms(List<Integer> bedroomIDs) {
        for(int i = 0; i < bedroomIDs.size();i++){
            int id = bedroomIDs.get(i);
            bedroomRepository.delete(bedroomRepository.findByRoomId(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Bedroom " + id +" not found")));
        }
    }

    public List<Bedroom> updateBedrooms(List<BedroomDTO> bedroomDTOs) {
        List<Bedroom> bedroomsRepo = new ArrayList<Bedroom>();
        for(int i = 0; i < bedroomDTOs.size();i++){
            int id = bedroomDTOs.get(i).getRoomId();
            Optional<Bedroom> bedroomUpdated = bedroomRepository.findByRoomId(id);
            if(bedroomDTOs.get(i).getRoomNumber() != null){
                bedroomUpdated.get().setRoomNumber(bedroomDTOs.get(i).getRoomNumber());
            }
            if(bedroomDTOs.get(i).getCapacity() != null){
                bedroomUpdated.get().setCapacity(bedroomDTOs.get(i).getCapacity());
            }
            if(bedroomDTOs.get(i).getFloor() != null ){
                bedroomUpdated.get().setFloor(bedroomDTOs.get(i).getFloor());
            }
            bedroomsRepo.add(bedroomRepository.save(bedroomUpdated.get()));
        }
        return bedroomsRepo;
    }
}
