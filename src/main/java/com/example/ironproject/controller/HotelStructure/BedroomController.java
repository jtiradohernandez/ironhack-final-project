package com.example.ironproject.controller.HotelStructure;

import com.example.ironproject.DTO.HotelStructure.BedroomDTO;
import com.example.ironproject.model.HotelStructure.Bedroom;
import com.example.ironproject.repository.HotelStructure.BedroomRepository;
import com.example.ironproject.service.HotelStructure.BedroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/hotel")
public class BedroomController {
    @Autowired
    private BedroomRepository bedroomRepository;

    @Autowired
    private BedroomService bedroomService;

    @GetMapping("/bedrooms")
    @ResponseStatus(HttpStatus.OK)
    public List<Bedroom> getBedrooms() {
        return bedroomService.getAllBedrooms();
    }

    @GetMapping("/bedrooms/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Bedroom> getBedroomById(@PathVariable(name="id") int bedroomId) {
        return bedroomService.getBedroomById(bedroomId);
    }

    @GetMapping("/{id}/bedrooms")
    @ResponseStatus(HttpStatus.OK)
    public List<Bedroom> getBedroomByHotelId(@PathVariable(name="id") int hotelId) {
        return bedroomService.getAllBedroomsOfHotel(hotelId);
    }

    @PostMapping("/bedrooms")
    @ResponseStatus(HttpStatus.CREATED)
    public List<Bedroom> addBedrooms(@RequestBody @Valid List<Bedroom> bedrooms) {
        return bedroomService.addBedrooms(bedrooms);
    }

    @PatchMapping("/bedrooms")
    @ResponseStatus(value = HttpStatus.OK)
    public List<Bedroom> updateBedrooms(@RequestBody @Valid List<BedroomDTO> bedroomDTOs){
        return bedroomService.updateBedrooms(bedroomDTOs);
    }

    @DeleteMapping("/bedrooms")
    @ResponseStatus(HttpStatus.OK)
    public void deleteBedrooms(@RequestBody List<Integer> bedroomIDs){
        bedroomService.deleteBedrooms(bedroomIDs);
    }
}
