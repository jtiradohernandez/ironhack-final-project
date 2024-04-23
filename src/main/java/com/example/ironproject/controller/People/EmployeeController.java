package com.example.ironproject.controller.People;

import com.example.ironproject.DTO.HotelStructure.BedroomDTO;
import com.example.ironproject.DTO.People.EmployeeDTO;
import com.example.ironproject.model.HotelStructure.Bedroom;
import com.example.ironproject.model.HotelStructure.Hotel;
import com.example.ironproject.model.People.Employee;
import com.example.ironproject.repository.People.EmployeeRepository;
import com.example.ironproject.service.People.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private UserService userService;

    @GetMapping("/hotel/employees")
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> getEmployees() {
        return null;
        //return userService.getAllHotels();
    }

    @GetMapping("/hotel/employees/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Employee> getEmployeeById(@PathVariable(name="id") int empoyeeId) {
        return null;
        //return hotelService.getHotelById(hotelId);
    }

    @GetMapping("/hotel/{id}/employees")
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> getEmployeeByHotelId(@PathVariable(name="id") int empoyeeId) {
        return null;
        //return bedroomService.getAllBedroomsOfHotel(hotelId);
    }

    @PostMapping("/hotel/employees")
    @ResponseStatus(HttpStatus.CREATED)
    public List<Employee> addEmployees(@RequestBody @Valid List<Employee> employees) {
        return null;
        //return bedroomService.addBedrooms(bedrooms);
    }

    @PatchMapping("/hotel/employees")
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> updateEmployees(@RequestBody @Valid List<EmployeeDTO> employeeDTOs){
        return null;
        //return bedroomService.updateBedrooms(bedroomDTOs);
    }

    @DeleteMapping("/hotel/employees")
    @ResponseStatus(HttpStatus.OK)
    public void deleteEmployees(@RequestBody List<Integer>employeeIDs){
        //bedroomService.deleteBedrooms(bedroomIDs);
    }
}
