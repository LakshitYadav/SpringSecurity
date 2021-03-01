package com.cognizant.springlearn.controller;

import java.util.ArrayList;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import javax.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.cognizant.springlearn.Country;
import com.cognizant.springlearn.SpringLearnApplication;
import com.cognizant.springlearn.service.CountryService;
import com.cognizant.springlearn.service.exception.CountryNotFoundException;

@RestController
public class CountryController {
	private static Logger LOGGER = LoggerFactory.getLogger(SpringLearnApplication.class);

	@Autowired
	CountryService countryService;

	@RequestMapping(value = "/country", method = RequestMethod.GET)
	public Country getCountryIndia() {
		LOGGER.info("START");
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("country.xml");
		Country India = context.getBean("in", Country.class);
		LOGGER.info("END");
		return India;
	}

	@RequestMapping(value = "/countries", method = RequestMethod.GET)
	public List getAllCountries() {
		LOGGER.info("START");
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("country.xml");
		ArrayList countries = context.getBean("countryList", ArrayList.class);
		LOGGER.info("END");
		return countries;
	}

	@RequestMapping(value = "/country/{code}", method = RequestMethod.GET)
	public Country getCountry(@PathVariable String code) throws CountryNotFoundException {
		LOGGER.info("START");
		Country country = countryService.getCountry(code);
		LOGGER.info("END");
		return country;
	}

	@PostMapping("/countries")
	public Country addCountry(@RequestBody @Valid Country country) {
		LOGGER.info("START");
		LOGGER.debug("Country: {}", country);
		
		/* ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		 Validator validator = factory.getValidator();
		 Set<ConstraintViolation<Country>> violations = validator.validate(country);
		 List<String> errors = new ArrayList<String>(); for
		 (ConstraintViolation<Country> violation : violations) {
		 errors.add(violation.getMessage()); } if (violations.size() > 0) { throw new
		 ResponseStatusException(HttpStatus.BAD_REQUEST, errors.toString()); }
		 */
		LOGGER.info("END");
		return country;
	}

	public CountryController() {
		LOGGER.debug("Inside Country Controller Constructor");
	}
}
