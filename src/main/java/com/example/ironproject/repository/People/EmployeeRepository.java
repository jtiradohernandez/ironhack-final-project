package com.example.ironproject.repository.People;

import com.example.ironproject.model.People.Client;
import com.example.ironproject.model.People.Employee;
import com.example.ironproject.model.Security.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository <Employee, String>{
    Employee findByUsername(String username);
    List<Employee> findByRoleEmployee(Role role);
}