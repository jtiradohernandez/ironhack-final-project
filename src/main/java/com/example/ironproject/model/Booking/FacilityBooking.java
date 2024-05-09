package com.example.ironproject.model.Booking;

import com.example.ironproject.model.HotelStructure.Facility;
import com.example.ironproject.model.HotelStructure.Room;
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
@PrimaryKeyJoinColumn(name="id")
public class FacilityBooking extends Bookings {
    @ManyToOne
    @JoinColumn(name="worker_assigned")
    private Employee workerAssigned;
    @NotEmpty(message = "The booking must be assigned to a service")
    @Enumerated(EnumType.STRING)
    private Service service;
    @NotEmpty(message = "The booking must be assigned to a slot")
    @Future
    private Date slot;
    @ManyToOne
    @JoinColumn(name="facility_booked")
    private Facility roomBooked;

    public FacilityBooking(Facility room, Client client, Service service, Date slot) {
        super(client);
        this.roomBooked = room;
        this.service = service;
        this.slot = slot;
    }
}
