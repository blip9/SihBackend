package com.taurus.sih.config;


import com.taurus.sih.model.Employee;
import com.taurus.sih.repositories.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    EmployeeRepo repo ;



    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Employee employee = repo.findEmployeeByEmail(email);
        if(employee==null){
            System.out.println("Employee not found");
            throw new UsernameNotFoundException("Employee not found");
        }
        return new EmployeePrincipal(employee);
    }
}
