package com.example.ironproject.repository.People;

import com.example.ironproject.model.People.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, String>{
    Optional<Client> findByDNI(String id);
}