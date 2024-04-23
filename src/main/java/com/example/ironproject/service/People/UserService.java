package com.example.ironproject.service.People;

import com.example.ironproject.model.People.Employee;
import com.example.ironproject.model.Security.Role;
import com.example.ironproject.repository.People.EmployeeRepository;
import com.example.ironproject.repository.Security.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.firewall.RequestRejectedException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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


    public Employee saveEmployee(Employee user) {
        log.info("Saving new user {} to the database", user.getName());
        // Encode the user's password for security before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return employeeRepository.save(user);
    }

    public Employee getEmployee(String username) {
        log.info("Fetching user {}", username);
        return employeeRepository.findByUsername(username);
    }
    public List<Employee> getEmployees() {
        log.info("Fetching all users");
        return employeeRepository.findAll();
    }
}

