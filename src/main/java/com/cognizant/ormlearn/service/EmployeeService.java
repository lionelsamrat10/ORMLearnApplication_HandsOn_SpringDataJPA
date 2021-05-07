package com.cognizant.ormlearn.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.cognizant.ormlearn.model.Employee;
import com.cognizant.ormlearn.repository.EmployeeRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class EmployeeService {
	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);
	@Autowired
	EmployeeRepository employeeRepository;

	// In each of the service class implement two methods one is to get
	// the entity based on id and the other one is to save the entity

	@Transactional
	public Employee get(int id) {
		LOGGER.info("Start!!");
		return employeeRepository.findById(id);
	}

	@Transactional
	public void save(Employee employee) {
		LOGGER.info("Start");
		employeeRepository.save(employee);
		LOGGER.info("End");
	}
	
	//Day-02 Session-02 HandsOn-02
	@Transactional
	public List<Employee> getAllPermanentEmployees(){
		LOGGER.info("Start!!");
		return employeeRepository.getAllPermanentEmployees();
	}
	//Day-02 Session-02 HandsOn-04
	@Transactional
	public double getAverageSalary() {
		LOGGER.info("Start!!");
		return employeeRepository.getAverageSalary(3);
	}
	
	//Day-02 Session-02 HandsOn-05
	//Get all employees using Native Query
	@Transactional
	public List<Employee> getAllEmployeesNative(){
		LOGGER.info("Start!!");
		return employeeRepository.getAllEmployeesNative();
	}
}
