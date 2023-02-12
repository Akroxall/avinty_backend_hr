package com.avinty.hr.repositories;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.avinty.hr.entities.Employee;
import com.avinty.hr.payload.request.EmployeeFilterRequest;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EmployeeSpecification implements Specification<Employee> {

    private static final long serialVersionUID = 1L;
    private EmployeeFilterRequest filterRequest;

    @Override
    public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        if (filterRequest != null) {
            if (filterRequest.getEmail() != null && !filterRequest.getEmail().isBlank()) {
                predicates.add(criteriaBuilder.like(root.get("email"), "%" + filterRequest.getEmail() + "%"));
            }

            if (filterRequest.getFullName() != null && !filterRequest.getFullName().isBlank()) {
                predicates.add(criteriaBuilder.like(root.get("fullName"), "%" + filterRequest.getFullName() + "%"));
            }
        }

        return criteriaBuilder.and(predicates.toArray(Predicate[]::new));

    }

}
