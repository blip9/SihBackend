package com.taurus.sih.controllers;

import com.taurus.sih.model.Employee;
import com.taurus.sih.service.EmployeeService;
import com.taurus.sih.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    JwtService jwtService;
    @Autowired
    AuthenticationManager authenticationManager;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/sign-up")
    public ResponseEntity<Employee> signUp(@RequestBody Employee employee) {
        employee.setPassword(encoder.encode(employee.getPassword()));
        employeeService.addEmployee(employee);
        return new ResponseEntity<>(employee, HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Employee employee) {
        try {
            Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(employee.getEmail(),employee.getPassword()));
            if(auth.isAuthenticated()){
                return jwtService.generateToken(employee.getEmail());

            }


        } catch (Exception e) {
            return new ResponseEntity<>("Authentication failed: " + e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
        return null;

    }
    @GetMapping("/get-employee/{token}")
    public ResponseEntity<Employee> getUser(@RequestHeader("Authorization") String token) {
        // Remove 'Bearer ' from the token if present
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        String email = jwtService.extractUserName(token);

        Employee employee = employeeService.getEmployee(email);
        if (employee != null) {
            employee.setPassword(null);
            return new ResponseEntity<>(employee, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/")
    public String apiStatus(){
        return "API is working.";
    }
}
