package com.employee.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.employee.api.model.Employee;
import com.employee.api.model.ResponseStatus;
import com.employee.api.repository.EmployeeRepository;

/**
 * 
 * @author Kirthy Saradhi Devarapalli
 *
 */
@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository repository;

	public void save(Employee emp) {
		repository.save(emp);
	}

	public Optional<Employee> findById(int id) {
		return repository.findById(id);
	}

	public List<Employee> findAll() {
		return repository.findAll();
	}

	public Page<Employee> findAll(Pageable pageable) {
		return repository.findAll(pageable);
	}

	public Long deleteById(int id) {
		return repository.deleteEmployeeById(id);
	}

	public ResponseStatus responseStatus(String description) {
		ResponseStatus resp = new ResponseStatus();
		resp.setStatus(HttpStatus.OK);
		resp.setDescription(description);
		return resp;
	}
}
