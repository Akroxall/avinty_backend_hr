package com.avinty.hr.dto;

import com.avinty.hr.entities.Department;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class DepartmentListDTO {
    private Integer id;
    private String name;

    public DepartmentListDTO(Department department) {
        if (department != null) {
            this.id = department.getId();
            this.name = department.getName();
        }
    }
}
