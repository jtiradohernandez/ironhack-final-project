package com.example.ironproject.model.Booking;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
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
@Inheritance(strategy= InheritanceType.TABLE_PER_CLASS)
public class BedroomBookings extends Bookings{
    @NotEmpty(message = "The booking must have an arrival date")
    @Future
    private Date arrivalDate;
    @NotEmpty(message = "The booking must have a departure date")
    @Future
    private Date departureDate;

    public BedroomBookings(int roomId, int clientId, Date arrivalDate, Date departureDate) {
        super(roomId, clientId);
        this.arrivalDate = arrivalDate;
        this.departureDate = departureDate;
    }
}
