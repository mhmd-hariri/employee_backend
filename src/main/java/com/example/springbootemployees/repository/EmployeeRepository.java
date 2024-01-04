package com.example.springbootemployees.repository;

import com.example.springbootemployees.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
 List<Employee> findByEmailId(String emailId);
 @Query("select case  when count(*) > 0 then true else false end from Employee e where e.emailId = ?1")
 Boolean selectExistsEmail(String email);

}
