package com.example.ironproject.model.Booking;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    int bookingId;
    @NotEmpty(message = "The booking must be assigned to a room")
    int roomId;
    @NotEmpty(message = "The booking must be assigned to a client")
    int clientId;

    public Bookings(int roomId, int clientId) {
        this.roomId = roomId;
        this.clientId = clientId;
    }
}
