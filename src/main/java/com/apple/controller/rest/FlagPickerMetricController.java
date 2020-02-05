package com.apple.controller.rest;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.apple.entity.FlagPicker;
import com.apple.entity.repository.FlagPickerRepository;
import com.apple.exception.ContinentNotFoundException;
import com.apple.exception.CountryNotFoundException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "FlagPickerMetricController" , description = "API to count number of times corresponding Rest API is been triggered by either continent or country.")
@RestController("/flagPicker/metricservice")
public class FlagPickerMetricController {	
	
	@Autowired
	private FlagPickerRepository repository;	
	
	@ApiOperation(value = "  -  API to view the count number of times API is trigged by country.")
	@GetMapping("/continent/country/fetch/count")
	public ResponseEntity<FlagPicker> continentCountryFlagFetchCount(){		
	
		Optional<FlagPicker> flagPickerOptional=repository.findByType("country");
		
		if(!flagPickerOptional.isPresent())
			 throw new CountryNotFoundException("No Country Fetch Count Found.");		
		
		return ResponseEntity.ok().body(flagPickerOptional.get());
	}
	
	@ApiOperation(value = "  -  API to view the count number of times API is trigged by continet.")
	@GetMapping("/continent/fetch/count")
	public ResponseEntity<FlagPicker[]> continentFetchCount(){	
	
		Optional<List<FlagPicker>> flagPickerOptional=repository.findAllByType("continent");
		
		if(!flagPickerOptional.isPresent())
			 throw new ContinentNotFoundException("No Continent Fetch Count Found.");		
		
		List<FlagPicker> flagPickerList=flagPickerOptional.get();
		FlagPicker[] flagPicker=new FlagPicker[flagPickerList.size()];
		return ResponseEntity.ok().body(flagPickerList.toArray(flagPicker));
	}
}
