package com.avinty.hr.dto;

import com.avinty.hr.entities.Employee;
import com.avinty.hr.entities.Position;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class EmployeeDTO {
    private String fullName;
    private String email;
    private Position position;

    public EmployeeDTO(Employee employee) {
        this.fullName = employee.getFullName();
        this.email = employee.getEmail();
        this.position = employee.getPosition();
    }

}
