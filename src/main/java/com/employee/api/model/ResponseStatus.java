package com.employee.api.model;

import org.springframework.http.HttpStatus;

import lombok.Data;

/**
 * 
 * @author Kirthy Saradhi Devarapalli
 *
 */
@Data
public class ResponseStatus {

	private HttpStatus status;

	private String description;
}
