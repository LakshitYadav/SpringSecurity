package com.cognizant.springlearn.dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.cognizant.springlearn.Employee;
import com.cognizant.springlearn.SpringLearnApplication;
import com.cognizant.springlearn.service.exception.EmployeeNotFoundException;

@Component
public class EmployeeDao {
	private static Logger LOGGER = LoggerFactory.getLogger(SpringLearnApplication.class);

	private static List<Employee> EMPLOYEE_LIST;

	public EmployeeDao() {
		LOGGER.debug("Inside EmployeeDao Constructor");
		ApplicationContext context = new ClassPathXmlApplicationContext("employee.xml");
		EMPLOYEE_LIST = (ArrayList<Employee>) context.getBean("employeeList");

	}

	public List<Employee> getAllEmployees() {
		LOGGER.info("START");
		LOGGER.info("END");
		return EMPLOYEE_LIST;
	}

	public void updateEmployee(Employee employee) throws EmployeeNotFoundException {
		LOGGER.info("START");
		for (Employee employeeInList : EMPLOYEE_LIST) {
			if (employeeInList.getId() == employee.getId()) {
				EMPLOYEE_LIST.remove(employeeInList);
				EMPLOYEE_LIST.add(employee);
				LOGGER.info("END");
				return;
			}
		}
		throw new EmployeeNotFoundException();
	}
	
	public void deleteEmployee(int id) throws EmployeeNotFoundException {
		LOGGER.info("START");
		for (Employee employeeInList : EMPLOYEE_LIST) {
			if (employeeInList.getId() == id) {
				EMPLOYEE_LIST.remove(employeeInList);
				LOGGER.info("END");
				return;
			}
		}
		throw new EmployeeNotFoundException();
	}
}
