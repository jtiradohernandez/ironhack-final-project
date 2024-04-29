package com.example.ironproject.controller.People;

import com.example.ironproject.DTO.People.ClientDTO;
import com.example.ironproject.DTO.People.EmployeeDTO;
import com.example.ironproject.model.People.Client;
import com.example.ironproject.model.People.Employee;
import com.example.ironproject.repository.People.ClientRepository;
import com.example.ironproject.service.People.ClientService;
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
    private ClientService clientService;

    @GetMapping("/hotel/clients")
    @ResponseStatus(HttpStatus.OK)
    public List<Client> getClients() {
        return clientService.getAllClients();
    }

    @GetMapping("/hotel/clients/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Client> getClientById(@PathVariable(name="id") String clientId) {
        return clientService.getClientById(clientId);
    }

    @GetMapping("/hotel/{id}/clients")
    @ResponseStatus(HttpStatus.OK)
    public List<Client> getClientByHotelId(@PathVariable(name="id") int hotelId) {
        return clientService.getAllClientsByHotel(hotelId);
    }

    @PostMapping("/hotel/clients")
    @ResponseStatus(HttpStatus.CREATED)
    public Client addClient(@RequestBody @Valid Client client) {
        return clientService.addClient(client);
    }

    @PatchMapping("/hotel/clients")
    @ResponseStatus(HttpStatus.OK)
    public Client updateClient(@RequestBody @Valid ClientDTO clientDTO){
        return clientService.updateClient(clientDTO);
    }

    @DeleteMapping("/hotel/clients")
    @ResponseStatus(HttpStatus.OK)
    public void deleteClients(@RequestBody List<Integer> clientIDs){
        //clientService.deleteBedrooms(bedroomIDs);
    }
}
