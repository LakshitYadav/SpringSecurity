package com.cognizant.springlearn;

import org.slf4j.LoggerFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cognizant.springlearn.Country;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.catalina.core.ApplicationContext;
import org.slf4j.Logger;

@SpringBootApplication
public class SpringLearnApplication {
	private static Logger LOGGER = LoggerFactory.getLogger(SpringLearnApplication.class);

	public static void main(String[] args) {
		LOGGER.info("Inside main");
		SpringApplication.run(SpringLearnApplication.class, args);
		displayDate();
		displayCountry();
		displayCountries();
	}

	public static void displayDate() {
		LOGGER.info("START");
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("date-format.xml");
		SimpleDateFormat format = context.getBean("dateFormat", SimpleDateFormat.class);
		try {
			Date date = format.parse("31/12/2018");
			// System.out.println(date);
			LOGGER.debug(date.toString());

		} catch (ParseException e) {
			e.printStackTrace();
		}
		LOGGER.info("END");

	}

	public static void displayCountry() {
		LOGGER.info("START");

		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("country.xml");

		Country country = (Country) context.getBean("country", Country.class);

		LOGGER.debug("Country : {}", country.toString());

		Country anotherCountry = context.getBean("country", Country.class);
		LOGGER.info("END");

	}

	public static void displayCountries() {
		LOGGER.info("START");

		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("country.xml");

		List countryList = (List) context.getBean("countryList", ArrayList.class);

		LOGGER.debug("Countries : {}", countryList.toString());
		LOGGER.info("END");

	}

}
