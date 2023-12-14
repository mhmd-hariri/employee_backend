package com.example.springbootemployees.validators;

import com.example.springbootemployees.repository.EmployeeRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqueEmailValidation implements ConstraintValidator<UniqueEmailValidator, String> {
   @Autowired
    EmployeeRepository employeeRepository;
    @Override
    public boolean isValid(String emailId, ConstraintValidatorContext constraintValidatorContext) {
        return employeeRepository.findByEmailId(emailId).isEmpty();
    }
}
