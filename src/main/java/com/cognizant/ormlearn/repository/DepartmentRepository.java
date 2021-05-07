package com.cognizant.ormlearn.repository;

import org.springframework.data.repository.CrudRepository;

import com.cognizant.ormlearn.model.Department;

public interface DepartmentRepository extends CrudRepository<Department, String> {
	Department findById(int id);
}
