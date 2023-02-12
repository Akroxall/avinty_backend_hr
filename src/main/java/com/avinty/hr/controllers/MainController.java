package com.avinty.hr.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.lang.Nullable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.avinty.hr.dto.DepartmentDTO;
import com.avinty.hr.dto.DepartmentListDTO;
import com.avinty.hr.dto.EmployeeDTO;
import com.avinty.hr.exceptions.NotFoundException;
import com.avinty.hr.payload.request.EmployeeFilterRequest;
import com.avinty.hr.services.DepartmentService;
import com.avinty.hr.services.EmployeeService;

@RestController
@RequestMapping(path = "/API/V1")
@CrossOrigin(origins = "http://localhost:5000")
public class MainController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;

    @GetMapping(path = "/employees", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<EmployeeDTO> getEmployees(@Nullable @RequestBody EmployeeFilterRequest getEmployeeRequest) {
        return employeeService.getEmployees(getEmployeeRequest);
    }

    @GetMapping(path = "/dep-emp", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DepartmentDTO> getDepartments() {
        return departmentService.getDepartments();
    }

    @GetMapping(path = "/department", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DepartmentListDTO> getDepartmentByName(@RequestParam String name) {
        return departmentService.getDepartmentByName(name);
    }

    @Secured({ "ROLE_ADMIN" })
    @PatchMapping(path = "/department/{departmentId}/set-manager/{employeeId}")
    public void setManager(@PathVariable Integer departmentId, @PathVariable Integer employeeId, Principal principal) throws NotFoundException {
        departmentService.setManager(departmentId, employeeId, principal.getName());
    }

    @Secured({ "ROLE_ADMIN" })
    @DeleteMapping(path = "/department/{departmentId}")
    public void deleteDepartment(@PathVariable Integer departmentId, Principal principal) throws NotFoundException {
        departmentService.deleteDepartment(departmentId, principal.getName());
    }

}
