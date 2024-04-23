package com.example.ironproject.DTO.People;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Data
public class PersonDTO {
    private String DNI;
    @NotEmpty(message = "name cannot be empty")
    private String name;
    @NotEmpty(message = "born date cannot be empty")
    private Date bornDate;
}
