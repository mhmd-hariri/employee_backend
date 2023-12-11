package com.example.springbootemployees.aop;

import com.example.springbootemployees.model.Employee;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Date;

@Aspect
@Component
public class EmployeeAspect {
    @Before(value = "execution(* com.example.springbootemployees.controller.EmployeeControler.*(..))")
    public void beforeControllerAdvice(JoinPoint joinpoint) {
        System.out.println("Request to " + joinpoint.getSignature()+ "started at" + new Date());
    }
    @After(value="execution(* com.example.springbootemployees.controller.EmployeeControler.*(..))")
    public void  afterControllerAdvice(JoinPoint joinPoint) {
        System.out.println("Request to "+ joinPoint.getSignature() + " ended at" + new Date());
    }
    @Before(value = "execution(* com.example.springbootemployees.service.EmployeeService.*(..))")
    public void beforeServiceAdvice(JoinPoint joinPoint){
        System.out.println("Request to " + joinPoint.getSignature() + "started at " + new Date() );
    }
    @After(value = "execution(* com.example.springbootemployees.service.EmployeeService.*(..))")
    public void  afterServiceAdvice(JoinPoint joinPoint) {
        System.out.println("Request to" + joinPoint.getSignature() + "ended at " + new Date());
    }

    @AfterReturning(value = "execution(* com.example.springbootemployees.service.EmployeeService.getEmployeeById(..))", returning = "employee")
    public  void afterReturnForGetByIdAdvice(JoinPoint joinPoint, ResponseEntity<Employee> employee){
        System.out.println("Business logic to get Employee with ID: " + employee.getBody().getId() + " ran seuccesfully");
    }
    @AfterThrowing(value = "execution(* com.example.springbootemployees.service.EmployeeService.getEmployeeById(..))", throwing = "exception")
    public void AfterThrowingForGetByIdAdvice(JoinPoint joinPoint, Exception exception) {
        System.out.println("Business Logic to fetch an employee ended with an exception  : " + exception.getMessage() );
    }
    @Around(value = "execution(* com.example.springbootemployees.service.EmployeeService.getEmployeeById(..))")
    public ResponseEntity<Employee> aroundAdviceForGetById(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("Inside Around Advice in Aspect : Business logic to fetch employee starte at"+ new Date());
        ResponseEntity<Employee> employee = null;
        try {
           /* Object [] args = new Object[1];
            long id = 5;
            args[0] = id;*/
            employee = (ResponseEntity<Employee>) proceedingJoinPoint.proceed();
            //employee = (ResponseEntity<Employee>) proceedingJoinPoint.proceed(args);
        } catch (Throwable ex){
            System.out.println("Inside Around Advice : Business logic to fethc employee faile terribly: "+ ex.getMessage());
        }
        System.out.println("Inside Around Advice in Aspect : Business logic to fetch employee ended at"+ new Date());
        return  employee;
    }

}
