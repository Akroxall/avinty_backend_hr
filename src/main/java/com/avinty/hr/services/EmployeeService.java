package com.avinty.hr.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avinty.hr.dto.EmployeeDTO;
import com.avinty.hr.entities.Employee;
import com.avinty.hr.payload.request.EmployeeFilterRequest;
import com.avinty.hr.repositories.EmployeeRepository;
import com.avinty.hr.repositories.EmployeeSpecification;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<EmployeeDTO> getEmployees(EmployeeFilterRequest employeeFilterRequest) {
        return employeeRepository.findAll(new EmployeeSpecification(employeeFilterRequest)).stream().map(EmployeeDTO::new).toList();
    }

    public Optional<Employee> getEmployeeByEmail(String email) {
        return employeeRepository.findByEmail(email);
    }

}
