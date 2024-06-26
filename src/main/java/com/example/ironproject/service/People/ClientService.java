package com.example.ironproject.service.People;

import com.example.ironproject.DTO.People.ClientDTO;
import com.example.ironproject.model.People.Client;
import com.example.ironproject.repository.HotelStructure.HotelRepository;
import com.example.ironproject.repository.People.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    ClientRepository clientRepository;

    @Autowired
    HotelRepository hotelRepository;

    public List<Client> getAllClients(){
        return clientRepository.findAll();
    }
    public Optional<Client> getClientById(String id){
        return clientRepository.findByDNI(id);
    }
    public Client addClient(Client client) {
        if(clientRepository.findByDNI(client.getDNI()).isEmpty()) {
            return clientRepository.save(client);
        }else {
            return null;
        }
    }

    public Client updateClient(ClientDTO clientDTO){
        Client clientUpdated = clientRepository.findByDNI(clientDTO.getDNI()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Client is not found"));
        if(clientDTO.getOrigin() != null){
            clientUpdated.setOrigin(clientDTO.getOrigin());
        }
        return clientRepository.save(clientUpdated);
    }
    public void deleteClient(String id){
        clientRepository.delete(clientRepository.findByDNI(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Client " + id +" is not found")));
    }
}
