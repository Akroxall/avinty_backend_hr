package com.avinty.hr.dto;

import java.util.List;

import com.avinty.hr.entities.Department;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class DepartmentDTO {
    private String name;
    private List<EmployeeDTO> employees;

    public DepartmentDTO(Department department) {
        this.name = department.getName();
        this.employees = department.getEmployees().stream().map(EmployeeDTO::new).toList();
    }

}
