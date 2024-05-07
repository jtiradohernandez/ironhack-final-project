package com.example.ironproject.model.Booking;

import com.example.ironproject.model.HotelStructure.Bedroom;
import com.example.ironproject.model.People.Client;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@PrimaryKeyJoinColumn(name="id")
public class BedroomBookings extends Bookings{
    @NotEmpty(message = "The booking must have an arrival date")
    @Future
    private Date arrivalDate;
    @NotEmpty(message = "The booking must have a departure date")
    @Future
    private Date departureDate;

    public BedroomBookings(Bedroom room, Client client, Date arrivalDate, Date departureDate) {
        super(room, client);
        this.arrivalDate = arrivalDate;
        this.departureDate = departureDate;
    }
}
