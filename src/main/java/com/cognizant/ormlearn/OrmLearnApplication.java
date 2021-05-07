package com.cognizant.ormlearn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import com.cognizant.ormlearn.model.Country;
import com.cognizant.ormlearn.model.Department;
import com.cognizant.ormlearn.model.Employee;
import com.cognizant.ormlearn.model.Skill;
import com.cognizant.ormlearn.service.CountryService;
import com.cognizant.ormlearn.service.DepartmentService;
import com.cognizant.ormlearn.service.EmployeeService;
import com.cognizant.ormlearn.service.SkillService;
import com.cognizant.ormlearn.service.exception.CountryNotFoundException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;

@SpringBootApplication
public class OrmLearnApplication {
	private static final Logger LOGGER = LoggerFactory.getLogger(OrmLearnApplication.class);
	// Include a static reference to CountryService in OrmLearnApplication class
	private static CountryService countryService;

	// Include static references of EmployeeService, DepartmentService and
	// SkillService in OrmLearnApplication.
	private static EmployeeService employeeService;
	private static DepartmentService departmentService;
	private static SkillService skillService;

	public static void main(String[] args) throws Exception {
		// SpringApplication.run(OrmLearnApplication.class, args);
		// Create applicationContext
		ApplicationContext context = SpringApplication.run(OrmLearnApplication.class, args);
		// Include logs for verifying if main() is called..
		LOGGER.info("Inside main!!");
		// Get the CountryService Bean
		countryService = context.getBean(CountryService.class);
		employeeService = context.getBean(EmployeeService.class);
		departmentService = context.getBean(DepartmentService.class);
		skillService = context.getBean(SkillService.class);
		// Call The testGetAllCountries()
		testGetAllCountries();
		// Call getAllCountriesTest
		getAllCountriesTest();
		// Call Add country
		testAddCountry();
		// Call the testUpdateCountry
		testUpdateCountry();
		// test the testDeleteCountry
		testDeleteCountry();
		// Test testFindAllByNameLike
		testFindCountryNameLike();
		// Test testFindAllByNameLikeAsc
		testFindAllByNameLikeAsc();
		// Test testFindByNameStartingWith
		testFindByNameStartingWith();
		// test testGetEmployee() Day 2 Session 1 HandsOn 4 part-01
		testGetEmployee();
		// test testAddEmployee() Day 2 Session 1 HandsOn 4 part-02
		testAddEmployee();
		// test testUpdateEmployee() Day 2 Session 1 HandsOn 4 part-02
		testUpdateEmployee();
		// test Day-02 testGetDepartment() Session-01 HandsOn-05 part-02
		testGetDepartment();
		// test Day-02 testGetDepartment() Session-01 HandsOn-06 part-02
		testAddSkillToEmployee();
		// test Day-02 testGetAllPermanentEmployees() Session-02 HandsOn-02 part-02
		testGetAllPermanentEmployees();
		//test Day-02 testGetAverageSalary() Session-02 HandsOn-04
		testGetAverageSalary();
		//Day-02 Session-02 HandsOn-05 testing this method
		testGetAllEmployeesNative();
	}

	// Test Method for getting all countries
	private static void testGetAllCountries() {
		LOGGER.info("Start");
		List<Country> countries = countryService.getAllCountries();
		LOGGER.debug("countries={}", countries);
		LOGGER.info("End");
	}

	// Test method for findCountryByCode
	private static void getAllCountriesTest() throws CountryNotFoundException {
		Country country = countryService.findCountryByCode("YE");
		LOGGER.debug("Country:{}", country);
		LOGGER.info("End");
	}

	// Test the addCountry method
	private static void testAddCountry() throws CountryNotFoundException {
		Country country = new Country();
		country.setCode("XY");
		country.setName("SAMRAT");
		countryService.addCountry(country);
		countryService.findCountryByCode("XY");
	}

	// Test the update country method
	private static void testUpdateCountry() {
		countryService.updateCountry("SAM", "XY");
	}

	// Test the deleteCountry method
	private static void testDeleteCountry() {
		countryService.deleteCountry("XY");
	}

	private static void testFindCountryNameLike() {
		LOGGER.info("Start!!");
		List<Country> countries = countryService.findByNameLike("%ou%");
		for (Country country : countries) {
			LOGGER.debug("Countries:{}", countries);
		}
	}

	private static void testFindAllByNameLikeAsc() {
		LOGGER.info("Start!!");
		List<Country> countries = countryService.findAllByNameLikeOrderByNameAsc("%ou%");
		for (Country country : countries) {
			LOGGER.debug("Countries:{}", countries);
		}
	}

	private static void testFindByNameStartingWith() {
		LOGGER.info("Start!!");
		List<Country> countries = countryService.findByNameStartsWith("Z");
		for (Country country : countries) {
			LOGGER.debug("Countries:{}", countries);
		}
	}

	// Day-02 Session-01 HandsOn-04 part-01
	private static void testGetEmployee() {
		LOGGER.info("Start");
		Employee employee = employeeService.get(1);
		LOGGER.debug("Employee:{}", employee);
		LOGGER.debug("Department:{}", employee.getDepartment());
		// Day-02 Session-01 HandsOn-06 part-01
		LOGGER.debug("Skills:{}", employee.getSkillList());
		LOGGER.info("End");
	}

	// Day-02 Session-01 HandsOn-04 part-02
	private static void testAddEmployee() throws ParseException {
		Employee employee = new Employee();
		// TODO: Come back here!!!!
		employee.setId(5);
		employee.setName("Samrat");
		employee.setSalary(28500);
		employee.setPermanent(true);
		Date date = new SimpleDateFormat("yyyy-MM-dd").parse("1999-08-28");
		employee.setDateOfBirth(date);
		// employee.setDepartment("Finance");
		employee.setDepartment(employeeService.get(1).getDepartment());
		employeeService.save(employee);
		Employee employee1 = employeeService.get(5);
		LOGGER.debug("Employee:{}", employee1);
		LOGGER.info("End!!");
	}

	// Day-02 Session-01 HandsOn-04 part-03
	private static void testUpdateEmployee() {
		LOGGER.info("Start");
		Employee employee = employeeService.get(1);
		Department department = departmentService.get(3);
		employee.setDepartment(department);
		employeeService.save(employee);
		LOGGER.debug("Employee:{}", employee);
		LOGGER.debug("Department:{}", employee.getDepartment());
		LOGGER.info("End");
	}

	// Day-02 Session-01 HandsOn-05 part-02
	private static void testGetDepartment() {
		LOGGER.info("Start!!");
		// Get the department detail for id = 1
		Department department = departmentService.get(3);
		// Log the department
		LOGGER.debug("Department:{}", department);
		// Log the list
		LOGGER.debug("Department:{}", department.getEmployeeList());
		LOGGER.info("End!!!");
	}

	// Day-02 Session-01 HandsOn-06 part-02
	// Add Skills to skill list
	private static void testAddSkillToEmployee() {
		LOGGER.info("Start!!");
		Employee employee = employeeService.get(1);
		Skill skill = skillService.get(2);
		// Add the skill to that employee, with empId = 1
		employee.getSkillList().add(skill);
		// Save the employeeDetails
		employeeService.save(employee);
		LOGGER.debug("Skills:{}", employee.getSkillList());
		LOGGER.info("End...");
	}

	// Day-02 Session-02 HandsOn-02 part-02
	public static void testGetAllPermanentEmployees() {
		LOGGER.info("Start");
		List<Employee> employees = employeeService.getAllPermanentEmployees();
		LOGGER.debug("Permanent Employees:{}", employees);
		employees.forEach(e -> LOGGER.debug("Skills:{}", e.getSkillList()));
		LOGGER.info("End");
	}
	//Day-02 Session-02 HandsOn-04
	private static void testGetAverageSalary() {
		LOGGER.debug("Average Salary:{}", employeeService.getAverageSalary());
		LOGGER.info("End!!!");
	}
	//Day-02 Session-02 HandsOn-05
	private static void testGetAllEmployeesNative(){
		LOGGER.debug("Employees: {}", employeeService.getAllEmployeesNative());
		LOGGER.info("End!!!");
	}
}
