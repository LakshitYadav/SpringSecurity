package com.cognizant.springlearn.dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.cognizant.springlearn.Department;
import com.cognizant.springlearn.Employee;
import com.cognizant.springlearn.SpringLearnApplication;

@Component
public class DepartmentDao {
	private static Logger LOGGER = LoggerFactory.getLogger(SpringLearnApplication.class);

	private static List<Department> DEPARTMENT_LIST;

	public DepartmentDao() {
		LOGGER.debug("Inside DepartmentDao constructor");
		ApplicationContext context = new ClassPathXmlApplicationContext("employee.xml");
		DEPARTMENT_LIST = (ArrayList<Department>) context.getBean("departmentList");

	}

	public List<Department> getAllDepartments() {
		LOGGER.info("START");
		LOGGER.info("END");
		return DEPARTMENT_LIST;
	}
}
