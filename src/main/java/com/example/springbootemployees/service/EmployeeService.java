package com.example.springbootemployees.service;

import com.example.springbootemployees.exception.ResourceNotFoundException;
import com.example.springbootemployees.model.Employee;
import com.example.springbootemployees.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    public List<Employee> getAllEmployee () {
        return  employeeRepository.findAll();
    }
    public Employee createEmployee(Employee employee){
        return employeeRepository.save(employee);
    }
    public ResponseEntity<Employee> getEmployeeById(Long id) throws Throwable{
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("employee not exist with id " + id));

        return ResponseEntity.ok(employee);
    }
    public ResponseEntity<Employee> updateEmployee(Long id, Employee employeeDetails) throws Throwable {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("employee not exist with id " + id));
        employee.setFirstName(employeeDetails.getFirstName());
        employee.setLastName(employeeDetails.getLastName());
        employee.setEmailId(employeeDetails.getEmailId());
        Employee updatedEmployee = employeeRepository.save(employee);
        return  ResponseEntity.ok(updatedEmployee);
    }
    public ResponseEntity<Map<String, Boolean>>deleteEmployee(long id) throws Throwable{
        Employee employee = employeeRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id: "+ id));
        employeeRepository.delete(employee);
        Map<String,Boolean> response = new HashMap<>();
        response.put("deleted",Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

}
