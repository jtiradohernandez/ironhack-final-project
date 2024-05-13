package com.example.ironproject.repository.People;

import com.example.ironproject.model.HotelStructure.Hotel;
import com.example.ironproject.model.People.Employee;
import com.example.ironproject.model.Security.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository <Employee, String>{


    Employee findByUsername(String username);
    List<Employee> findByRoleEmployee(Role role);
    List<Employee> findEmployeeByHotelAssigned(Optional<Hotel> byHotelId);

    Optional<Employee> findByDNI(String employeeId);
}