package com.employee.api.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.api.exception.EmployeeNotFoundException;
import com.employee.api.model.Employee;
import com.employee.api.model.ResponseStatus;
import com.employee.api.service.EmployeeService;
import com.employee.api.service.SequenceGeneratorService;

/**
 * 
 * @author Kirthy Saradhi Devarapalli
 *
 */
@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private EmployeeService service;

	@Autowired
	private SequenceGeneratorService seqGenService;

	@PostMapping("/add")
	public ResponseEntity<ResponseStatus> addEmployee(@RequestBody Employee e) {
		e.setId(seqGenService.sequenceGenerator(Employee.SEQUENCE_NAME));
		service.save(e);

		return new ResponseEntity<>(service.responseStatus("New Employee added with Id " + e.getId()), HttpStatus.OK);
	}

	@GetMapping("/find/{id}")
	public ResponseEntity<Employee> getEmployee(@PathVariable int id) {

		Optional<Employee> emp = service.findById(id);

		if (emp.isEmpty()) {
			throw new EmployeeNotFoundException();
		}

		return new ResponseEntity<>(emp.get(), HttpStatus.OK);
	}

	@GetMapping("/findAll")
	public ResponseEntity<List<Employee>> getAllEmployee() {

		List<Employee> allEmployees = service.findAll();

		if (allEmployees.isEmpty())
			throw new EmployeeNotFoundException();

		return new ResponseEntity<>(allEmployees, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ResponseStatus> deleteEmployee(@PathVariable int id) {

		Long deletedEmp = service.deleteById(id);

		if (deletedEmp == 0)
			throw new EmployeeNotFoundException();

		return new ResponseEntity<>(service.responseStatus("Employee deleted with Id " + id), HttpStatus.OK);

	}

	@PutMapping("/update")
	public ResponseEntity<ResponseStatus> updateEmployee(@RequestBody Employee e) {

		service.save(e);

		return new ResponseEntity<>(service.responseStatus("Updated Employee for Id " + e.getId()), HttpStatus.OK);

	}

	@GetMapping("/findPageable")
	public Page<Employee> employeesPageable(Pageable pageable) {

		Page<Employee> allEmployees = service.findAll(pageable);

		if (allEmployees.isEmpty())
			throw new EmployeeNotFoundException();

		return allEmployees;

	}

}
