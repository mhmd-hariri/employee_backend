package com.example.springbootemployees.controller;

import com.example.springbootemployees.UpdateValidationGroup;
import com.example.springbootemployees.model.Employee;
import com.example.springbootemployees.service.EmployeeService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = {})
@SecurityRequirement(name = "bearerAuth")
@Tag(name="Employee")
@RestController
@RequestMapping("/api/v1/")
public class EmployeeControler {
    @Autowired
    private EmployeeService employeeService;
    // chron job every one minute at second 10
    //@Scheduled(cron = "0 42 8 12 * ?",zone = "Europe/Paris")
    //scheduled task every 10 seconds
   // @Scheduled(fixedRate = 10000)
    //scheduled task with fixed delay 5 seconds und start with delay 15 sec
    //@Scheduled(fixedDelay = 5000, initialDelay = 15000)
    @GetMapping("employees")
    List<Employee> getAllEmployee () {
        return  employeeService.getAllEmployee();
    }
    //@Hidden
    @PostMapping("employees")
    public Employee createEmployee(@Validated(UpdateValidationGroup.class) @RequestBody Employee employee){
        return employeeService.createEmployee(employee);
    }
    //get employee by id rest api
    @Operation(
            description = "Get endpoint for manager",
            summary = "This is a summary for  employee get endpoint",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorized or Invalid Token",
                            responseCode = "403"
                    )
            }/*,
            parameters = {
                    @Parameter(
                            name = "ID",
                            description = "Identifier for employee that we want fetch from database",
                            required = true,
                            allowEmptyValue = false,
                            schema = @Schema(
                                    minimum = "0"
                            )
                    )

            }*/
    )
    @GetMapping("employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) throws Throwable{
        return employeeService.getEmployeeById(id);
    }
    // update employee rest api
    @PutMapping("employees/{id}")
    @ApiResponse(description =
            "Invalid request data:\n"
                    + "  1. Request body empty.\n"
                    + "  2. Attributes in body do not match constraints described in schema.\n",
    responseCode = "400")
    @ApiResponse(
            description = "Unauthorized Request oder missing JWT auth token",
            responseCode = "403")
    @ApiResponse(
            description = " Successful response",
            responseCode = "200"
    )
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @Validated(UpdateValidationGroup.class) @RequestBody Employee employeeDetails) throws Throwable {

        return  employeeService.updateEmployee(id,employeeDetails);
    }
    // delete employee rest api
    @DeleteMapping("employees/{id}")
    public ResponseEntity<Map<String, Boolean>>deleteEmployee(@PathVariable long id) throws Throwable{
      return  employeeService.deleteEmployee(id);
    }
}
