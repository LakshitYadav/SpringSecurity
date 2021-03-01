package com.cognizant.springlearn.service;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.springlearn.Employee;
import com.cognizant.springlearn.SpringLearnApplication;
import com.cognizant.springlearn.dao.EmployeeDao;
import com.cognizant.springlearn.service.exception.EmployeeNotFoundException;

@Service
public class EmployeeService {
	private static Logger LOGGER = LoggerFactory.getLogger(SpringLearnApplication.class);

	@Autowired
	private EmployeeDao employeeDao;

	@Transactional
	public List<Employee> getAllEmployees() {
		LOGGER.info("START");
		LOGGER.info("END");
		return employeeDao.getAllEmployees();
	}

	public void updateEmployee(Employee employee) throws EmployeeNotFoundException {
		LOGGER.info("START");
		LOGGER.info("END");
		employeeDao.updateEmployee(employee);
	}
	public void deleteEmployee(int id) throws EmployeeNotFoundException {
		LOGGER.info("START");
		LOGGER.info("END");
		employeeDao.deleteEmployee(id);
	}
}
