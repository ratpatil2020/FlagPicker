package com.apple.controller.rest;

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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "FlagPickerController" , description = "Operations pertaining to Continents,Country and corresponding flag.")
@RestController
@RequestMapping("/flagPicker")
public class FlagPickerController {  
	
	@Autowired
	private ContinentService continentService;
	
	@Autowired
	private FlagPickerMetricService flagFickerMetricService;
	
	
	@ApiOperation(value = "  -  View a list of available continents,countries and corresponding country flag.", response = ContinentDTO[].class)
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successfully retrieved list"),
	        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	      }
	)	
	@GetMapping("/continents")	
	public ResponseEntity<ContinentDTO[]> getAllContinentsAndCountries(){
			
		ContinentDTO[] continentDTOs=continentService.getAllContinentsAndCountries();
		
		return ResponseEntity.ok().body(continentDTOs);
	}

	@ApiOperation(value = "  -  Search continent,countries and corresponding country flag by continent.", response = ContinentDTO.class)
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successfully retrieved Continent Object"),
	        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	      }
	)	
	@GetMapping("/continents/{continent}/countries")
	public ResponseEntity<ContinentDTO> getAllCountriesByContinent(@PathVariable("continent") String continent){
		
		ContinentDTO continentDTO=continentService.getAllCountriesByContinent(continent);
		
		flagFickerMetricService.incrementContinentCounter();
		flagFickerMetricService.saveORUpdateFlagPickerMetric("continent");		
						
		return ResponseEntity.ok().body(continentDTO);
	}
	
	@ApiOperation(value = "  -  Search country flag by country.", response = CountryDTO.class)	
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successfully retrieved Country Object"),
	        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	      }
	)	
	@GetMapping("/continents/{country}/flag")
	public ResponseEntity<CountryDTO> getCountryFlagByCountry(@PathVariable("country") String country){
		
		CountryDTO countryDTO=continentService.getCountryFlag(country);
		
		flagFickerMetricService.incrementCountryFlagCounter();		
		flagFickerMetricService.saveORUpdateFlagPickerMetric("country");		
		
		return ResponseEntity.ok().body(countryDTO);
	}	
}