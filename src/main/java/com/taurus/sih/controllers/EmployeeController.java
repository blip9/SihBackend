package com.taurus.sih.controllers;

import com.taurus.sih.model.Employee;
import com.taurus.sih.model.Token;
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
    public ResponseEntity<Token> login(@RequestBody Employee employee) {
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(employee.getEmail(), employee.getPassword())
            );

            if (auth.isAuthenticated()) {
                // Generate the token
                String tokenValue = jwtService.generateToken(employee.getEmail()).getBody();

                // Create a Token object to hold the token
                Token token = new Token(tokenValue);

                // Return the Token object in the response
                return new ResponseEntity<>(token, HttpStatus.OK);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return null;
    }
    @PostMapping("/get-employee")
    public ResponseEntity<Employee> getUser(@RequestBody Token jwttoken) {
        String  token = jwttoken.getToken();
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
