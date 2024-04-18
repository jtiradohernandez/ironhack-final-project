package com.example.ironproject.service.HotelStructure;

import com.example.ironproject.model.HotelStructure.Bedroom;
import com.example.ironproject.model.HotelStructure.Facility;
import com.example.ironproject.repository.HotelStructure.FacilityRepository;
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
    FacilityRepository facilityRepository;
    public List<Facility> getAllFacilities(){
        return facilityRepository.findAll();
    }

    public List<Facility> getAllFacilitiesOfHotel(int hotelId){
        return facilityRepository.findFacilitiesByHotelId(hotelId);
    }

    public Optional<Facility> getFacilityById(int id){
        return facilityRepository.findByFacilityId(id);
    }

    public List<Facility> addFacilities(List<Facility> facilities) {
        List<Facility> facilitiesRepo = new ArrayList<Facility>();
        for(int i = 0; i <= facilities.size();i++){
            facilitiesRepo.add(facilityRepository.save(facilities.get(i)));
        }
        return facilitiesRepo;
    }

    public void deleteFacilities(List<int> facilityIDs) {
        for(int i = 0; i <= facilityIDs.size();i++){
            int id = facilityIDs.get(i);
            facilityRepository.delete(facilityRepository.findByFacilityId(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Facility " + id +" not found")));
        }
    }
}
