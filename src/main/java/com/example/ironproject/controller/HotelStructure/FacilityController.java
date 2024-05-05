package com.example.ironproject.controller.HotelStructure;

import com.example.ironproject.DTO.HotelStructure.FacilityDTO;
import com.example.ironproject.model.HotelStructure.Facility;
import com.example.ironproject.service.HotelStructure.FacilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/hotel")
public class FacilityController {

    @Autowired
    private FacilityService facilityService;

    @GetMapping("/facilities")
    @ResponseStatus(HttpStatus.OK)
    public List<Facility> getFacilities() {
        return facilityService.getAllFacilities();
    }

    @GetMapping("/facilities/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Facility> getFacilityById(@PathVariable(name="id") int facilityId) {
        return facilityService.getFacilityById(facilityId);
    }

    @GetMapping("/{id}/facilities")
    @ResponseStatus(HttpStatus.OK)
    public List<Facility> getFacilitiesByHotelId(@PathVariable(name="id") int hotelId) {
        return facilityService.getAllFacilitiesOfHotel(hotelId);
    }

    @PostMapping("/facilities")
    @ResponseStatus(HttpStatus.CREATED)
    public List<Facility> addFacilities(@RequestBody @Valid List<Facility> facilities) {
        return facilityService.addFacilities(facilities);
    }

    @PatchMapping("/facilities")
    @ResponseStatus(HttpStatus.OK)
    public List<Facility> updateFacilities(@RequestBody @Valid List<FacilityDTO> facilitiesDTOs){
        return facilityService.updateFacilities(facilitiesDTOs);
    }

    @DeleteMapping("/facilities")
    @ResponseStatus(HttpStatus.OK)
    public void deleteFacilities(@RequestBody List<Integer> facilityIDs){
        facilityService.deleteFacilities(facilityIDs);
    }
}
