package com.example.ironproject.controller.HotelStructure;

import com.example.ironproject.model.HotelStructure.Bedroom;
import com.example.ironproject.model.HotelStructure.Hotel;
import com.example.ironproject.repository.HotelStructure.BedroomRepository;
import com.example.ironproject.repository.HotelStructure.HotelRepository;
import com.example.ironproject.service.HotelStructure.BedroomService;
import com.example.ironproject.service.HotelStructure.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class BedroomController {
    @Autowired
    BedroomRepository bedroomRepository;

    BedroomService bedroomService;

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

    @GetMapping("/bedrooms/hotel/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Bedroom> getBedroomByHotelId(@PathVariable(name="id") int hotelId) {
        return bedroomService.getAllBedroomsOfHotel(hotelId);
    }
}
