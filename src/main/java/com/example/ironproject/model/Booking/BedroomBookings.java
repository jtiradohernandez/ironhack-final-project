package com.example.ironproject.model.Booking;

import com.example.ironproject.model.HotelStructure.Bedroom;
import com.example.ironproject.model.People.Client;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import java.time.format.DateTimeFormatter;
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
    @ManyToOne
    @JoinColumn(name="bedroom_booked")
    private Bedroom roomBooked;

    public BedroomBookings(Bedroom room, Client client, Date arrivalDate, Date departureDate) {
        super(client);
        this.roomBooked = room;
        this.arrivalDate = arrivalDate;
        this.departureDate = departureDate;
    }
}
