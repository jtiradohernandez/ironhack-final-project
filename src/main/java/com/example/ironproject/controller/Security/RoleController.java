package com.example.ironproject.controller.Security;

import com.example.ironproject.DTO.Security.RoleToUserDTO;
import com.example.ironproject.model.People.Employee;
import com.example.ironproject.service.People.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/hotel")
public class RoleController {

    @Autowired
    private UserService userService;

    @PostMapping("/employees/role")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addRoleToUser(@RequestBody RoleToUserDTO roleToUserDTO) {
        userService.addRoleToUser(roleToUserDTO.getUsername(), roleToUserDTO.getRoleName());
    }

    @PatchMapping("/employees/password/{id}/{new_password}")
    @ResponseStatus(HttpStatus.OK)
    public Employee updateEmployeePassword(@PathVariable(name="id") String employeeId,@PathVariable(name="new_password") String newPassword){
        return userService.updateEmployeePassword(employeeId, newPassword);
    }
}
