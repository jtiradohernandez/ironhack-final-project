package com.example.ironproject.controller.Security;

import com.example.ironproject.DTO.Security.RoleToUserDTO;
import com.example.ironproject.model.Security.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class RoleController {

    //@Autowired
    //private UserServiceInterface userService;

    @PostMapping("/hotel/roles/addtouser")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addRoleToUser(@RequestBody RoleToUserDTO roleToUserDTO) {
       // employeesService.addRoleToUser(roleToUserDTO.getUsername(), roleToUserDTO.getRoleName());
    }


}
