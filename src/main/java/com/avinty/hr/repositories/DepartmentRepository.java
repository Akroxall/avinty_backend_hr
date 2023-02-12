package com.avinty.hr.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.avinty.hr.entities.Department;

public interface DepartmentRepository extends CrudRepository<Department, Integer> {

    @Override
    List<Department> findAll();

    List<Department> findByNameContainsIgnoreCase(String name);

}
