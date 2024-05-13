package com.example.ironproject.model.Booking;

import com.example.ironproject.model.People.Person;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Inheritance(strategy=InheritanceType.JOINED)
abstract class Bookings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookingId;
    @NotEmpty(message = "The booking must be assigned to a room")
    @NotEmpty(message = "The booking must be assigned to a client")
    @ManyToOne
    @JoinColumn(name="client_of_booking")
    private Person clientOfBooking;

    public Bookings(Person client) {
        this.clientOfBooking = client;
    }
}
