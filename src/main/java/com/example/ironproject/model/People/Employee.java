package com.example.ironproject.model.People;

import com.example.ironproject.enumeration.Jobs;
import com.example.ironproject.model.HotelStructure.Hotel;
import com.example.ironproject.model.Security.Role;
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
@NoArgsConstructor
@Entity
public class Employee extends Person{
    @NotEmpty(message = "employee must have a job assigned")
    private Jobs job;
    @ManyToOne
    @JoinColumn(name="role_employee")
    private Role roleEmployee;
    private String username;
    private String password;
    @NotEmpty(message = "employee must be assigned to an hotel")
    @ManyToOne
    @JoinColumn(name="hotel_assigned")
    private Hotel hotelAssigned;

    public Employee(String DNI, String name, Date bornDate, Jobs job, Role roleEmployee, String username, String password, Hotel hotelAssigned) {
        super(DNI, name, bornDate);
        this.job = job;
        this.roleEmployee = roleEmployee;
        this.username = username;
        this.password = password;
        this.hotelAssigned = hotelAssigned;
    }


}
