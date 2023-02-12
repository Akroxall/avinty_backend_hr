package com.avinty.hr.security.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.avinty.hr.entities.Employee;
import com.avinty.hr.entities.Position;
import com.avinty.hr.services.EmployeeService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmployeeUserDetailService implements UserDetailsService {

    @Autowired
    private EmployeeService employeeService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Employee employee = employeeService.getEmployeeByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Cannot find user by the given email [" + email + "]"));
        return new User(employee.getEmail(), employee.getPassword(), getRoleByPositon(employee.getPosition()));
    }

    private List<GrantedAuthority> getRoleByPositon(Position employeePosition) {
        List<GrantedAuthority> authorities = new ArrayList<>();

        if (employeePosition.equals(Position.CEO) || employeePosition.equals(Position.MANAGER)) {
            authorities.add(new SimpleGrantedAuthority(EmployeeAuthorityRole.ROLE_ADMIN.name()));
        }
        else {
            authorities.add(new SimpleGrantedAuthority(EmployeeAuthorityRole.ROLE_USER.name()));
        }

        return authorities;
    }

}
