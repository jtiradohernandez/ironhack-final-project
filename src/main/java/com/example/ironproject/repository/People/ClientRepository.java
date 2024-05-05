package com.example.ironproject.repository.People;

import com.example.ironproject.model.HotelStructure.Bedroom;
import com.example.ironproject.model.HotelStructure.Hotel;
import com.example.ironproject.model.People.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, String>{


    Optional<Client> findByDNI(String id);

    //List<Client> findClientsByHotelId(Optional<Hotel> byHotelId); //TODO crear una busqueda que me devuelva el hotel en base a las habitaciones
}