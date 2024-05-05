package com.example.ironproject.DTO.People;

import com.example.ironproject.model.HotelStructure.Bedroom;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class ClientDTO extends PersonDTO{
    private String Origin;
}
