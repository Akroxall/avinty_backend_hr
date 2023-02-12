package com.avinty.hr.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.avinty.hr.entities.Employee;
import com.avinty.hr.entities.Position;

public class ManagerValidator implements ConstraintValidator<ManagerPositonValidation, Employee> {

    @Override
    public boolean isValid(Employee employee, ConstraintValidatorContext context) {
        return employee == null || employee.getPosition().equals(Position.MANAGER);
    }

}
