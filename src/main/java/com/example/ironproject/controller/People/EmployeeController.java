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
        return userService.getEmployees();
    }

    @GetMapping("/hotel/employees/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Employee> getEmployeeById(@PathVariable(name="id") String employeeId) {
        return userService.getEmployeeById(employeeId);
    }

    @GetMapping("/hotel/{id}/employees")
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> getEmployeeByHotelId(@PathVariable(name="id") int hotelId) {
        return userService.getAllEmployeesOfHotel(hotelId);
    }

    @PostMapping("/hotel/employees")
    @ResponseStatus(HttpStatus.CREATED)
    public Employee addEmployees(@RequestBody @Valid Employee employee) {
        return userService.addEmployee(employee);
    }

    @PatchMapping("/hotel/employees")
    @ResponseStatus(HttpStatus.OK)
    public Employee updateEmployee(@RequestBody @Valid EmployeeDTO employeeDTO){
        return userService.updateEmployee(employeeDTO);
    }

    @DeleteMapping("/hotel/employees")
    @ResponseStatus(HttpStatus.OK)
    public void deleteEmployee(@RequestBody String employeeID){
        userService.deleteEmployee(employeeID);
    }
}
