package com.example.ironproject.model.People;

import com.example.ironproject.model.HotelStructure.Bedroom;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Client extends Person{
    @ManyToOne
    @JoinColumn(name="bedroom_assigned")
    private Bedroom bedroomAssigned;
    @NotEmpty(message = "client should have a origin")
    private String Origin;
    public Client(String DNI, String name, Date bornDate, String origin) {
        super(DNI, name, bornDate);
        Origin = origin;
    }
}
