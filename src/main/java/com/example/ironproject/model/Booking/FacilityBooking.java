package com.example.ironproject.model.Booking;

import com.example.ironproject.model.HotelStructure.Facility;
import com.example.ironproject.model.People.Client;
import com.example.ironproject.model.People.Employee;
import jakarta.persistence.*;
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
    @ManyToOne
    @JoinColumn(name="worker_assigned")
    private Employee workerAssigned;
    @NotEmpty(message = "The booking must be assigned to a service")
    private Service service;
    @NotEmpty(message = "The booking must be assigned to a slot")
    @Future
    private Date slot;

    public FacilityBooking(Facility room, Client client, Service service, Date slot) {
        super(room, client);
        this.service = service;
        this.slot = slot;
    }
}
