package com.example.springbootemployees.controller;

import com.example.springbootemployees.model.Employee;
import com.example.springbootemployees.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = {})
@RestController
@RequestMapping("/api/v1/")
public class EmployeeControler {
    @Autowired
    private EmployeeService employeeService;
    @GetMapping("employees")
    List<Employee> getAllEmployee () {
        return  employeeService.getAllEmployee();
    }
    @PostMapping("employees")
    public Employee createEmployee(@RequestBody Employee employee){
        return employeeService.createEmployee(employee);
    }
    //get employee by id rest api
    @GetMapping("employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) throws Throwable{
        return employeeService.getEmployeeById(id);
    }
    // update employee rest api
    @PutMapping("employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails) throws Throwable {

        return  employeeService.updateEmployee(id,employeeDetails);
    }
    // delete employee rest api
    @DeleteMapping("employees/{id}")
    public ResponseEntity<Map<String, Boolean>>deleteEmployee(@PathVariable long id) throws Throwable{
      return  employeeService.deleteEmployee(id);
    }
}
