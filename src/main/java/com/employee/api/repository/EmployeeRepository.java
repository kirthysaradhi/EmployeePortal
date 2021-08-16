package com.employee.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.employee.api.model.Employee;

/**
 * 
 * @author Kirthy Saradhi Devarapalli
 *
 */
public interface EmployeeRepository extends MongoRepository<Employee, Integer> {

	Long deleteEmployeeById(int id);

}
