package com.example.ironproject.controller.People;

import com.example.ironproject.DTO.People.EmployeeDTO;
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
@RequestMapping("/api/hotel")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private UserService userService;

    @GetMapping("/employees")
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> getEmployees() {
        return userService.getEmployees();
    }

    @GetMapping("/employees/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Employee> getEmployeeById(@PathVariable(name="id") String employeeId) {
        return userService.getEmployeeById(employeeId);
    }

    @GetMapping("/{id}/employees")
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> getEmployeeByHotelId(@PathVariable(name="id") int hotelId) {
        return userService.getAllEmployeesOfHotel(hotelId);
    }

    @PostMapping("/employees")
    @ResponseStatus(HttpStatus.CREATED)
    public Employee addEmployee(@RequestBody @Valid Employee employee) {
        return userService.addEmployee(employee);
    }

    @PatchMapping("/employees")
    @ResponseStatus(HttpStatus.OK)
    public Employee updateEmployee(@RequestBody @Valid EmployeeDTO employeeDTO){
        return userService.updateEmployee(employeeDTO);
    }

    @DeleteMapping("/employees/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteEmployee(@PathVariable(name="id") String employeeId){
        userService.deleteEmployee(employeeId);
    }
}
