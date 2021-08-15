package com.employee.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 
 * @author Kirthy Saradhi Devarapalli
 *
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No Employee ID Found for the Respective Operation")
public class EmployeeNotFoundException extends RuntimeException {

}
