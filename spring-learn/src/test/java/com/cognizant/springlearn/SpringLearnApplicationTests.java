package com.cognizant.springlearn;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import com.cognizant.springlearn.controller.CountryController;
import com.cognizant.springlearn.controller.EmployeeController;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.junit.Assert.assertNotNull;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.ResultActions;

@SpringBootTest
@AutoConfigureMockMvc
class SpringLearnApplicationTests {
	@Autowired
	private CountryController countryController;
	@Autowired
	private EmployeeController employeeController;
	@Autowired
	private MockMvc mvc;

	@Test
	public void contextLoads() {

		assertNotNull(countryController);
		assertNotNull(employeeController);

	}

	@Test
	public void testGetCountry() throws Exception {
		ResultActions actions = mvc.perform(get("/country"));
		actions.andExpect(status().isOk());
		actions.andExpect(jsonPath("$.code").exists());
		actions.andExpect(jsonPath("$.code").value("IN"));
		actions.andExpect(jsonPath("$.name").exists());
		actions.andExpect(jsonPath("$.name").value("India"));
	}

	@Test
	public void testGetCountryException() throws Exception {
		ResultActions actions = mvc.perform(get("/country/nz"));
		actions.andExpect(status().isNotFound());
		actions.andExpect(status().reason("Country not found"));
	}

	@Test
	public void testUpdateEmployeeException() throws Exception {
		Department department = new Department();
		department.setId(2);
		department.setName("Testing");
		Skill skill = new Skill();
		skill.setId(3);
		skill.setName("JUnit");
		Set<Skill> skillList = new HashSet<Skill>();
		skillList.add(skill);
		Employee employee = new Employee();
		employee.setId(7);
		employee.setName("Sam");
		employee.setDateOfBirth(new Date("25/10/1999"));
		employee.setSalary(50000);
		employee.setDepartment(department);
		employee.setSkillList(skillList);

		ResultActions actions = mvc.perform(
				post("/updateEmployee").contentType(MediaType.APPLICATION_JSON).content(asJsonString(employee)));
		actions.andExpect(status().isNotFound());
		actions.andExpect(status().reason("Employee not found"));
	}

	public static String asJsonString(Employee employee) {
		try {
			return new ObjectMapper().writeValueAsString(employee);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	public void testDeleteEmployeeException() throws Exception {
		ResultActions actions = mvc.perform(delete("/deleteEmployee/7"));
		actions.andExpect(status().isNotFound());
		actions.andExpect(status().reason("Employee not found"));
	}
}
