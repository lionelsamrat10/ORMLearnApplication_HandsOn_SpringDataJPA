package com.cognizant.ormlearn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.ormlearn.model.Country;

//Annotate withh @Repository
@Repository
public interface CountryRepository extends JpaRepository<Country, String> {
	//Find all names that contains a substring
	List<Country> findAllByNameLike(String countryNameSubString);
	//Find all names in sorted order
	List<Country> findAllByNameLikeOrderByNameAsc(String countryNameSubString);
	//Find all the names that starts with an alphabet
	List<Country> findByNameStartsWith(String alphabet);
}
