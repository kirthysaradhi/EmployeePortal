package com.employee.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.employee.api.model.Employee;
import com.employee.api.repository.EmployeeRepository;
import com.employee.api.service.EmployeeService;

/**
 * 
 * @author Kirthy Saradhi Devarapalli
 *
 */
@SpringBootTest
public class EmployeePortalApplicationTests {

	@Autowired
	private EmployeeService service;

	@MockBean
	private EmployeeRepository repository;

	@Test
	public void findAllTest() {
		when(repository.findAll())
				.thenReturn(Stream
						.of(new Employee(5, "Kirthy", "Saradhi", "Senior Java Consultant", 67),
								new Employee(6, "Kirthy", "Devarapalli", "Java Architect", 78))
						.collect(Collectors.toList()));
		assertEquals(2, service.findAll().size());
	}

	@Test
	public void findByIdTest() {
		int id = 78;
		when(repository.findById(id))
				.thenReturn(Optional.of(new Employee(id, "Kirthy", "Saradhi", "Senior Java Consultant", 67)));
		assertEquals(id, service.findById(id).get().getId());
	}

	@Test
	public void saveEmployeeTest() {
		Employee emp = new Employee(5, "Kirthy", "Saradhi", "Senior Java Consultant", 67);
		service.save(emp);
		verify(repository, times(1)).save(emp);
	}

	@Test
	public void deleteByIdTest() {
		int id = 78;
		when(repository.deleteEmployeeById(id)).thenReturn(10L);
		assertEquals(10, service.deleteById(id));
	}

}
