package com.avinty.hr.services;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avinty.hr.dto.DepartmentDTO;
import com.avinty.hr.dto.DepartmentListDTO;
import com.avinty.hr.entities.Department;
import com.avinty.hr.entities.Employee;
import com.avinty.hr.exceptions.DepartmentNotFoundException;
import com.avinty.hr.exceptions.EmployeeNotFoundException;
import com.avinty.hr.exceptions.NotFoundException;
import com.avinty.hr.repositories.DepartmentRepository;
import com.avinty.hr.repositories.EmployeeRepository;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    public List<DepartmentDTO> getDepartments() {
        return departmentRepository.findAll().stream().map(DepartmentDTO::new).toList();
    }

    public List<DepartmentListDTO> getDepartmentByName(String name) {
        return departmentRepository.findByNameContainsIgnoreCase(name).stream().map(DepartmentListDTO::new).toList();
    }

    public void setManager(Integer departmentId, Integer employeeId, String modifierEmail) throws NotFoundException {
        Department department = getDepartmentById(departmentId);
        Employee employee = getEmployeeById(employeeId);
        Employee modifierEmployee = getEmployeeByMail(modifierEmail);

        department.setManager(employee);
        department.setModifiedAt(Instant.now());
        department.setModifiedBy(modifierEmployee);
        departmentRepository.save(department);

    }

    public void deleteDepartment(Integer departmentId, String modifierEmail) throws NotFoundException {
        Employee modifierEmployee = getEmployeeByMail(modifierEmail);
        Department department = getDepartmentById(departmentId);
        department.getEmployees().forEach(e -> modify(e, modifierEmployee));
        departmentRepository.delete(department);
    }

    private void modify(Employee e, Employee modifierEmployee) {
        e.setDepartment(null);
        e.setModifiedBy(modifierEmployee);
        e.setModifiedAt(Instant.now());
    }

    private Department getDepartmentById(Integer departmentId) throws DepartmentNotFoundException {
        return departmentRepository.findById(departmentId).orElseThrow(() -> new DepartmentNotFoundException("Department not found with [" + departmentId + "] id!"));
    }

    private Employee getEmployeeById(Integer employeeId) throws EmployeeNotFoundException {
        return employeeRepository.findById(employeeId).orElseThrow(() -> new EmployeeNotFoundException("Employee not found with [" + employeeId + "] id!"));
    }

    private Employee getEmployeeByMail(String mail) throws EmployeeNotFoundException {
        return employeeRepository.findByEmail(mail).orElseThrow(() -> new EmployeeNotFoundException("Employee not found with [" + mail + "] mail!"));
    }
}
