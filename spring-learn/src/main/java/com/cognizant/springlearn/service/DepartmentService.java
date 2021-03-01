package com.cognizant.springlearn.service;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.springlearn.Department;
import com.cognizant.springlearn.Employee;
import com.cognizant.springlearn.SpringLearnApplication;
import com.cognizant.springlearn.dao.DepartmentDao;
import com.cognizant.springlearn.dao.EmployeeDao;

@Service
public class DepartmentService {
	private static Logger LOGGER = LoggerFactory.getLogger(SpringLearnApplication.class);

	@Autowired
	private DepartmentDao departmentDao;

	@Transactional
	public List<Department> getAllDepartments() {
		LOGGER.info("START");
		LOGGER.info("END");

		return departmentDao.getAllDepartments();
	}
}
