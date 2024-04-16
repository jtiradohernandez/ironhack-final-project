package com.example.ironproject.controller.HotelStructure;

import com.example.ironproject.model.HotelStructure.Bedroom;
import com.example.ironproject.model.HotelStructure.Space;
import com.example.ironproject.service.HotelStructure.SpaceService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class SpaceController {

    SpaceService spaceService;

    @GetMapping("/spaces")
    @ResponseStatus(HttpStatus.OK)
    public List<Space> getSpaces() {
        return spaceService.getAllSpaces();
    }

    @GetMapping("/spaces/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Space> getSpaceById(@PathVariable(name="id") int spaceId) {
        return spaceService.getSpaceById(spaceId);
    }

    @GetMapping("/spaces/hotel/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Space> getSpacesByHotelId(@PathVariable(name="id") int hotelId) {
        return spaceService.getAllSpacesOfHotel(hotelId);
    }
}
