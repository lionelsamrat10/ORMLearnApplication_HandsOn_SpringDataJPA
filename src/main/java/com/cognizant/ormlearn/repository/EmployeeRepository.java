package com.cognizant.ormlearn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.cognizant.ormlearn.model.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, String>{
	//Day-02 Session-02
	Employee findById(int id);
	//Day-02 Session-02 HandsOn-02
	//Get all permanent employees using HQL (Hibernate Query Language)
	@Query(value="SELECT e FROM Employee e left join fetch e.department d left join fetch e.skillList WHERE e.permanent = 1")
	List<Employee> getAllPermanentEmployees();
	
	//Day-02 Session-02 HandsOn-04
	@Query(value="SELECT AVG(e.salary) FROM Employee e where e.department.id = :id")
	double getAverageSalary(@Param("id") int id);
	
	//Day-02 Session-02 HandsOn-05
	//Get all employees using Native Query
	@Query(value="SELECT * FROM employee", nativeQuery = true)
	List<Employee> getAllEmployeesNative();
}
