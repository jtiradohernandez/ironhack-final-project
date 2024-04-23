package com.example.ironproject.model.People;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
abstract class Person {
    @Id
    private String DNI;
    @NotEmpty(message = "name cannot be empty")
    private String name;
    @NotEmpty(message = "born date cannot be empty")
    private Date bornDate;

    public Person(String DNI, String name, Date bornDate) {
        this.DNI = DNI;
        this.name = name;
        this.bornDate = bornDate;
    }
}
