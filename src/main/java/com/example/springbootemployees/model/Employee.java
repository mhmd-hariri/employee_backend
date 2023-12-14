package com.example.springbootemployees.model;

import com.example.springbootemployees.UpdateValidationGroup;
import com.example.springbootemployees.validators.UniqueEmailValidator;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

import java.lang.annotation.Documented;

@Entity
@Table(name= "employees")
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Schema(title = "Employee Id", description = "employee identifier as unique ID")
	private long id;
	@Column(name = "firstname")
	@Length(min=5,max = 20,message = "the firstname should be between 5 and 20 letters")
	@NotBlank(message = "firstname should not be blank")
	private String firstName;
	@Column(name = "lastname")
	@Length(min=5,max = 20,message = "the lastname should be between 5 and 20 letters")
	@NotBlank(message = "lastname should not be blank")
	private String lastName;
	@Column(name = "emailid")
	@Schema(title = "Email Id", description = "Email Id must be unique email and valid one")
	@Email(message = "email must be valid one")
	@UniqueEmailValidator(message = "email must be unique", groups = UpdateValidationGroup.class)
	@NotBlank(message = "email should not be blank")
	private String emailId;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

}
