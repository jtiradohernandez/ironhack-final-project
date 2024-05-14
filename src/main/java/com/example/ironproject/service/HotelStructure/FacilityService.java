package com.example.ironproject.service.HotelStructure;

import com.example.ironproject.DTO.HotelStructure.FacilityDTO;
import com.example.ironproject.model.HotelStructure.Facility;
import com.example.ironproject.repository.HotelStructure.FacilityRepository;
import com.example.ironproject.repository.HotelStructure.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FacilityService {

    @Autowired
    private FacilityRepository facilityRepository;
    @Autowired
    private HotelRepository hotelRepository;
    public List<Facility> getAllFacilities(){
        return facilityRepository.findAll();
    }

    public List<Facility> getAllFacilitiesOfHotel(int hotelId){
        return facilityRepository.findFacilitiesByRoomOfHotel(hotelRepository.findByHotelId(hotelId));
    }

    public Optional<Facility> getFacilityById(int id){
        return facilityRepository.findByRoomId(id);
    }

    public List<Facility> addFacilities(List<Facility> facilities) {
        List<Facility> facilitiesRepo = new ArrayList<Facility>();
        for(int i = 0; i < facilities.size();i++){
            if(facilityRepository.findByRoomId(facilities.get(i).getRoomId()).isEmpty()) {
                facilitiesRepo.add(facilityRepository.save(facilities.get(i)));
            }
        }
        return facilitiesRepo;
    }

    public void deleteFacilities(List<Integer> facilityIDs) {
        for(int i = 0; i < facilityIDs.size();i++){
            int id = facilityIDs.get(i);
            facilityRepository.delete(facilityRepository.findByRoomId(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Facility " + id +" is not found")));
        }
    }

    public List<Facility> updateFacilities(List<FacilityDTO> facilitiesDTOs) {
        List<Facility> facilitiesRepo = new ArrayList<Facility>();
        for(int i = 0; i < facilitiesDTOs.size();i++){
            int id = facilitiesDTOs.get(i).getRoomId();
            Optional<Facility> facilityUpdated = facilityRepository.findByRoomId(id);
            if(!facilitiesDTOs.get(i).getName().isEmpty()){
                facilityUpdated.get().setName(facilitiesDTOs.get(i).getName());
            }
            if(facilitiesDTOs.get(i).getCanBeBooked() != null){
                facilityUpdated.get().setCanBeBooked(facilitiesDTOs.get(i).getCanBeBooked());
            }
            if(facilitiesDTOs.get(i).getCapacity() != null){
                facilityUpdated.get().setCapacity(facilitiesDTOs.get(i).getCapacity());
            }
            if(facilitiesDTOs.get(i).getFloor() != null ){
                facilityUpdated.get().setFloor(facilitiesDTOs.get(i).getFloor());
            }
            facilitiesRepo.add(facilityRepository.save(facilityUpdated.get()));
        }
        return facilitiesRepo;
    }
}
