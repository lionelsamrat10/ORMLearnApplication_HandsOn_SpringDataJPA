package com.cognizant.ormlearn.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.ormlearn.model.Country;
import com.cognizant.ormlearn.repository.CountryRepository;
import com.cognizant.ormlearn.service.exception.CountryNotFoundException;

@Service // Contains Business Logic, the service class
public class CountryService {
	@Autowired // Autowiring the CountryRepository Class
	CountryRepository countryRepository;

	// Create Transactional methods
	// Get all the countries
	@Transactional
	public List<Country> getAllCountries() {
		return countryRepository.findAll();
	}

	// get country by id
	@Transactional
	public Country findCountryByCode(String countryCode) throws CountryNotFoundException {
		Optional<Country> result = countryRepository.findById(countryCode);
		// If result is not found, then throw an exception
		if (!result.isPresent()) {
		}
		Country country = result.get();
		return country;
	}

	// Add a new Country
	@Transactional
	public void addCountry(Country country) {
		countryRepository.save(country);
	}

	// Update country based on code
	@Transactional
	public void updateCountry(String countryName, String countryCode) {
		Optional<Country> country = countryRepository.findById(countryCode);
		if (country.isPresent()) {
			country.get().setName(countryName);
			countryRepository.save(country.get());
		}
	}

	// Create the Delete method
	@Transactional
	public void deleteCountry(String countryCode) {
		countryRepository.deleteById(countryCode);
	}

	// Find all countries that contain some substring
	@Transactional
	public List<Country> findByNameLike(String countryNameSubString) {
		return countryRepository.findAllByNameLike(countryNameSubString);
	}

	// Find all countries, that is having some substring but in ascending order
	@Transactional
	public List<Country> findAllByNameLikeOrderByNameAsc(String countryNameSubString) {
		return countryRepository.findAllByNameLikeOrderByNameAsc(countryNameSubString);
	}

	// Find all the countries, that starts with some given alphabet
	@Transactional
	public List<Country> findByNameStartsWith(String alphabet) {
		return countryRepository.findByNameStartsWith(alphabet);
	}
}
