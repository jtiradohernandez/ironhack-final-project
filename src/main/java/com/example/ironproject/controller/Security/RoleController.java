package com.example.ironproject.controller.Security;

import com.example.ironproject.DTO.People.EmployeeDTO;
import com.example.ironproject.DTO.Security.RoleToUserDTO;
import com.example.ironproject.model.People.Employee;
import com.example.ironproject.model.Security.Role;
import com.example.ironproject.service.People.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class RoleController {

    @Autowired
    private UserService userService;

    @PostMapping("/hotel/employees/role")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addRoleToUser(@RequestBody RoleToUserDTO roleToUserDTO) {
        userService.addRoleToUser(roleToUserDTO.getUsername(), roleToUserDTO.getRoleName());
    }

    @PatchMapping("/hotel/employees/password/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Employee updateEmployeePassword(@PathVariable(name="id") String employeeId, @RequestBody @Valid String newPassword){
        return userService.updateEmployeePassword(employeeId, newPassword);
    }


}
