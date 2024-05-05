package com.example.ironproject.model.Booking;

import com.example.ironproject.model.HotelStructure.Bedroom;
import com.example.ironproject.model.HotelStructure.Hotel;
import com.example.ironproject.model.HotelStructure.Room;
import com.example.ironproject.model.People.Client;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
@Entity
abstract class Bookings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookingId;
    @NotEmpty(message = "The booking must be assigned to a room")
    @ManyToOne
    @JoinColumn(name="room_booked")
    private Room roomBooked;
    @NotEmpty(message = "The booking must be assigned to a client")
    @ManyToOne
    @JoinColumn(name="client_of_booking")
    private Client clientOfBooking;

    public Bookings(Room room, Client client) {
        this.roomBooked = room;
        this.clientOfBooking = client;
    }
}
