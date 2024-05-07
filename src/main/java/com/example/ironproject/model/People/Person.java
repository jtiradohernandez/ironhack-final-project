package com.example.ironproject.model.People;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Inheritance(strategy=InheritanceType.JOINED)
abstract class Person {
    @Id
    private String DNI;
    @NotEmpty(message = "name cannot be empty")
    private String name;
    @NotEmpty(message = "born date cannot be empty")
    @Past
    private Date bornDate;

    public Person(String DNI, String name, Date bornDate) {
        this.DNI = DNI;
        this.name = name;
        this.bornDate = bornDate;
    }
}
