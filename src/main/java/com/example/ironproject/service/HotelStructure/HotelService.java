package com.example.ironproject.service.HotelStructure;

import com.example.ironproject.DTO.HotelStructure.HotelDTO;
import com.example.ironproject.model.HotelStructure.Hotel;
import com.example.ironproject.repository.HotelStructure.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    public List<Hotel> getAllHotels(){
        return hotelRepository.findAll();
    }

    public Optional<Hotel> getHotelById(int id){
        return hotelRepository.findByHotelId(id);
    }

    public List<Hotel> addHotels(List<Hotel> hotels) {
        List<Hotel> hotelsRepo = new ArrayList<Hotel>();
        for(int i = 0; i <= hotels.size();i++){
            hotelsRepo.add(hotelRepository.save(hotels.get(i)));
        }
        return hotelsRepo;
    }

    public void deleteHotels(List<Integer> hotelIDs) {
        for(int i = 0; i <= hotelIDs.size();i++){
            int id = hotelIDs.get(i);
            hotelRepository.delete(hotelRepository.findByHotelId(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Hotel " + id +" not found")));
        }
    }

    public List<Hotel> updateHotels(List<HotelDTO> hotelDTOs) {
        List<Hotel> hotelsRepo = new ArrayList<Hotel>();
        for(int i = 0; i <= hotelDTOs.size();i++){
            int id = hotelDTOs.get(i).getHotelId();
            Optional<Hotel> hotelUpdated = hotelRepository.findByHotelId(id);
            if(!hotelDTOs.get(i).getAddress().isEmpty()){
                hotelUpdated.get().setAddress(hotelDTOs.get(i).getAddress());
            }
            if(!hotelDTOs.get(i).getName().isEmpty()){
                hotelUpdated.get().setName(hotelDTOs.get(i).getName());
            }
            if(!hotelDTOs.get(i).getPlanet().isEmpty()){
                hotelUpdated.get().setPlanet(hotelDTOs.get(i).getPlanet());
            }
            if(!hotelDTOs.get(i).getRegion().isEmpty()){
                hotelUpdated.get().setRegion(hotelDTOs.get(i).getRegion());
            }
            if(hotelDTOs.get(i).getCapacity() != null ){
                hotelUpdated.get().setRegion(hotelDTOs.get(i).getRegion());
            }
            hotelsRepo.add(hotelRepository.save(hotelUpdated.get()));
        }
        return hotelsRepo;
    }
}
