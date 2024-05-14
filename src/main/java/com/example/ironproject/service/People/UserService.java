package com.example.ironproject.service.People;

import com.example.ironproject.DTO.People.EmployeeDTO;
import com.example.ironproject.model.People.Employee;
import com.example.ironproject.model.Security.Role;
import com.example.ironproject.repository.HotelStructure.HotelRepository;
import com.example.ironproject.repository.People.EmployeeRepository;
import com.example.ironproject.repository.Security.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.firewall.RequestRejectedException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService implements UserDetailsService {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private HotelRepository hotelRepository;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Retrieve user with the given username
        Employee user = employeeRepository.findByUsername(username);
        // Check if user exists
        if (user == null) {
            log.error("User not found in the database");
            throw new UsernameNotFoundException("User not found in the database");
        } else {
            log.info("User found in the database: {}", username);
            // Create a collection of SimpleGrantedAuthority objects from the user's roles
            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(user.getRoleEmployee().getName()));
            // Return the user details, including the username, password, and authorities
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
        }
    }

    public void addRoleToUser(String username, String roleName) {
        log.info("Adding role {} to user {}", roleName, username);
        Employee user = employeeRepository.findByUsername(username);
        Role role = roleRepository.findByName(roleName);
        if(user.getRoleEmployee().equals("ROLE_ADMIN")){
            List<Employee> adminEmployees = employeeRepository.findByRoleEmployee(role);
            if (adminEmployees.size()>1){
                user.setRoleEmployee(role);
                employeeRepository.save(user);
            }else{
                throw new RequestRejectedException("This user is the only admin, should be at least one more");
            }
        }else {
            user.setRoleEmployee(role);
            employeeRepository.save(user);
        }
    }

    public Role saveRole(Role role) {
        log.info("Saving new role {} to the database", role.getName());
        return roleRepository.save(role);
    }

    public List<Employee> getEmployees() {
        log.info("Fetching all users");
        return employeeRepository.findAll();
    }

    public Optional<Employee> getEmployeeById(String employeeId) {
        return employeeRepository.findByDNI(employeeId);
    }

    public Employee getEmployee(String username) {
        log.info("Fetching user {}", username);
        return employeeRepository.findByUsername(username);
    }

    public List<Employee> getAllEmployeesOfHotel(int hotelId) {
        return employeeRepository.findEmployeeByHotelAssigned(hotelRepository.findByHotelId(hotelId));
    }

    public Employee addEmployee(Employee user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return employeeRepository.save(user);
    }

    public Employee updateEmployee(EmployeeDTO employeeDTO) {
        Employee employeeUpdated = employeeRepository.findById(employeeDTO.getDNI()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee is not found"));
        if(employeeDTO.getJob() != null){
            employeeUpdated.setJob(employeeDTO.getJob());
        }
        if(employeeDTO.getHotelAssigned() != null){
            employeeUpdated.setHotelAssigned(employeeDTO.getHotelAssigned());
        }
        return employeeRepository.save(employeeUpdated);
    }

    public void deleteEmployee(String employeeId){
        employeeRepository.delete(employeeRepository.findById(employeeId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee " + employeeId +" is not found")));
    }

    public Employee updateEmployeePassword(String id, String newPassword) {
        Employee user = employeeRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee " + id +" is not found"));
        log.info("Saving new password to the database");
        user.setPassword(passwordEncoder.encode(newPassword));
        return employeeRepository.save(user);
    }
}

