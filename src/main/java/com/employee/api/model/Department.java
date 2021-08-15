package com.employee.api.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 
 * @author Kirthy Saradhi Devarapalli
 *
 */
@Getter
@Setter
@ToString
@Document(collection = "Department")
public class Department {

	@Id
	private int departmentId;

	private String departmentName;

}
