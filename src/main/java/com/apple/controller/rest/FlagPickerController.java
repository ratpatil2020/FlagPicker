package com.apple.controller.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.apple.bo.ContinentDTO;
import com.apple.bo.CountryDTO;
import com.apple.service.ContinentService;
import com.apple.service.FlagPickerMetricService;
import io.micrometer.core.annotation.Timed;

@RestController
@RequestMapping("/flagPicker")
public class FlagPickerController {   
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FlagPickerController.class);
	
	@Autowired
	private ContinentService continentService;
	
	@Autowired
	private FlagPickerMetricService flagFickerMetricService;
	
	@GetMapping("/continents")
	@Timed("http.server.requests")
	public ResponseEntity<ContinentDTO[]> getAllContinentsAndCountries(){
		String method="getAllContinentsAndCountries() : ";
		LOGGER.info(method+" started ...");
		
		ContinentDTO[] continentDTOs=continentService.getAllContinentsAndCountries();	
		
		LOGGER.info(method+" ended ...");
		
		return ResponseEntity.ok().body(continentDTOs);
	}

	@GetMapping("/continents/{continent}/countries")
	public ResponseEntity<ContinentDTO> getAllCountriesByContinent(@PathVariable("continent") String continent){
		
		String method="getAllCountriesByContinent() : ";
		LOGGER.info(method+" started ...");
		
		ContinentDTO continentDTO=continentService.getAllCountriesByContinent(continent);
		
		flagFickerMetricService.incrementContinentCounter();
		flagFickerMetricService.saveORUpdateFlagPickerMetric("continent");		
		
		LOGGER.info(method+" ended ...");
		
		return ResponseEntity.ok().body(continentDTO);
	}
	
	@GetMapping("/continents/{country}/flag")
	public ResponseEntity<CountryDTO> getCountryFlagByCountry(@PathVariable("country") String country){
		
		String method="getCountryFlagByCountry() : ";
		LOGGER.info(method+" started ...");
		
		CountryDTO countryDTO=continentService.getCountryFlag(country);
		
		flagFickerMetricService.incrementCountryFlagCounter();		
		flagFickerMetricService.saveORUpdateFlagPickerMetric("country");		
		
		LOGGER.info(method+" ended ...");
		
		return ResponseEntity.ok().body(countryDTO);
	}	
}