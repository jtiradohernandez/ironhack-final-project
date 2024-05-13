package com.example.ironproject.controller.People;

import com.example.ironproject.DTO.People.ClientDTO;
import com.example.ironproject.model.People.Client;
import com.example.ironproject.repository.People.ClientRepository;
import com.example.ironproject.service.People.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/hotel")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ClientService clientService;

    @GetMapping("/clients")
    @ResponseStatus(HttpStatus.OK)
    public List<Client> getClients() {
        return clientService.getAllClients();
    }

    @GetMapping("/clients/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Client> getClientById(@PathVariable(name="id") String clientId) {
        return clientService.getClientById(clientId);
    }

    @PostMapping("/clients")
    @ResponseStatus(HttpStatus.CREATED)
    public Client addClient(@RequestBody @Valid Client client) {
        return clientService.addClient(client);
    }

    @PatchMapping("/clients")
    @ResponseStatus(HttpStatus.OK)
    public Client updateClient(@RequestBody @Valid ClientDTO clientDTO){
        return clientService.updateClient(clientDTO);
    }

    @DeleteMapping("/clients/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteClients(@PathVariable(name="id") String clientID){
        clientService.deleteClient(clientID);
    }
}
