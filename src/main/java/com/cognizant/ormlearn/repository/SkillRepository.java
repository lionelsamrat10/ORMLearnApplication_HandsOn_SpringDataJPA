package com.cognizant.ormlearn.repository;

import org.springframework.data.repository.CrudRepository;

import com.cognizant.ormlearn.model.Skill;

public interface SkillRepository extends CrudRepository<Skill, String>{
	Skill findById(int id);
}
