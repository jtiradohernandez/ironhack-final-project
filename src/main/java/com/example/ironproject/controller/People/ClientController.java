package com.example.ironproject.controller.People;

import com.example.ironproject.DTO.People.ClientDTO;
import com.example.ironproject.DTO.People.EmployeeDTO;
import com.example.ironproject.model.People.Client;
import com.example.ironproject.model.People.Employee;
import com.example.ironproject.repository.People.ClientRepository;
import com.example.ironproject.service.People.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private UserService userService;

    @GetMapping("/hotel/clients")
    @ResponseStatus(HttpStatus.OK)
    public List<Client> getClients() {
        return null;
        //return userService.getAllHotels();
    }

    @GetMapping("/hotel/clients/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Client> getClientById(@PathVariable(name="id") int clientId) {
        return null;
        //return hotelService.getHotelById(hotelId);
    }

    @GetMapping("/hotel/{id}/clients")
    @ResponseStatus(HttpStatus.OK)
    public List<Client> getClientByHotelId(@PathVariable(name="id") int clientId) {
        return null;
        //return bedroomService.getAllBedroomsOfHotel(hotelId);
    }

    @PostMapping("/hotel/clients")
    @ResponseStatus(HttpStatus.CREATED)
    public List<Client> addClients(@RequestBody @Valid List<Client> clients) {
        return null;
        //return bedroomService.addBedrooms(bedrooms);
    }

    @PatchMapping("/hotel/clients")
    @ResponseStatus(HttpStatus.OK)
    public List<Client> updateClients(@RequestBody @Valid List<ClientDTO> clientDTOs){
        return null;
        //return bedroomService.updateBedrooms(bedroomDTOs);
    }

    @DeleteMapping("/hotel/clients")
    @ResponseStatus(HttpStatus.OK)
    public void deleteClients(@RequestBody List<Integer> clientIDs){
        //bedroomService.deleteBedrooms(bedroomIDs);
    }
}
