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
public class FacilityBooking extends Bookings {

    int workerId;
    @NotEmpty(message = "The booking must be assigned to a service")
    Service service;
    @NotEmpty(message = "The booking must be assigned to a slot")
    @Future
    Date slot;

    public FacilityBooking(int roomId, int clientId, Service service, Date slot) {
        super(roomId, clientId);
        this.service = service;
        this.slot = slot;
    }
}
