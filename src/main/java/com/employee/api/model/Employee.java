package com.employee.api.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author Kirthy Saradhi Devarapalli
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Employee")
public class Employee {

	@Transient
	public static final String SEQUENCE_NAME = "employee_sequence";

	@Id
	private int id;

	private String firstName;

	private String lastName;

	private String role;

	private int departmentId;

}
