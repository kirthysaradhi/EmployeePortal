package com.employee.api;

import java.nio.charset.Charset;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import com.employee.api.controller.EmployeeController;
import com.employee.api.model.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * 
 * @author Kirthy Saradhi Devarapalli
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeControllerTest {

	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	@Autowired
	private MockMvc mockMvc;

	@InjectMocks
	private EmployeeController controller;

	@Before
	public void setup() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	public void saveEmployeeTest() throws Exception {
		String url = "/api/employee/add";
		Employee emp = new Employee(5, "Kirthy", "Saradhi", "Senior Java Consultant", 67);
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(emp);

		mockMvc.perform(post(url).contentType(APPLICATION_JSON_UTF8).content(requestJson)).andExpect(status().isOk());
	}

	@Test
	public void updateEmployeeTest() throws Exception {
		String url = "/api/employee/update";
		Employee emp = new Employee(5, "Kirthy", "Saradhi", "Senior Java Consultant", 67);
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(emp);

		mockMvc.perform(put(url).contentType(APPLICATION_JSON_UTF8).content(requestJson)).andExpect(status().isOk());
	}

	@Test
	public void findEmployeesTest() throws Exception {
		mockMvc.perform(get("/api/employee/findAll")).andExpect(status().isOk());
	}

	@Test
	public void findEmployeeByIdTest() throws Exception {
		mockMvc.perform(get("/api/employee/find/9")).andExpect(status().isOk());
	}

	@Test
	public void deleteEmployeeByIdTest() throws Exception {
		mockMvc.perform(delete("/api/employee/delete/5")).andExpect(status().isOk());
	}

}
