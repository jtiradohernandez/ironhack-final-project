package com.example.ironproject.model.People;

import com.example.ironproject.model.HotelStructure.Bedroom;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Inheritance(strategy= InheritanceType.TABLE_PER_CLASS)
public class Client extends Person{
    @NotEmpty(message = "client should have a origin")
    private String Origin;
    public Client(String DNI, String name, Date bornDate, String origin) {
        super(DNI, name, bornDate);
        Origin = origin;
    }
}
