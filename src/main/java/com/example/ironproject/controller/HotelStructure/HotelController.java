package com.example.ironproject.controller.HotelStructure;

import com.example.ironproject.model.HotelStructure.Hotel;
import com.example.ironproject.repository.HotelStructure.HotelRepository;
import com.example.ironproject.service.HotelStructure.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class HotelController {

    @Autowired
    HotelRepository hotelRepository;

    HotelService hotelService;

    @GetMapping("/hotel")
    @ResponseStatus(HttpStatus.OK)
    public List<Hotel> getHotels() {
        return hotelService.getAllHotels();
    }

    @GetMapping("/hotel/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Hotel> getHotelById(@PathVariable(name="id") int hotelId) {
        return hotelService.getHotelById(hotelId);
    }
}
