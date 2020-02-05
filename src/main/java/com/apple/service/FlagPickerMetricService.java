package com.apple.service;

import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apple.entity.FlagPicker;
import com.apple.entity.repository.FlagPickerRepository;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;


@Service
public class FlagPickerMetricService {
  
	@Autowired
    private MeterRegistry meterRegistry;
	
	@Autowired
	private FlagPickerRepository repository;
	
	private Counter continentCounter;
	
	private Counter countryFlagCounter;
	
	@PostConstruct
	private void initFlagFickerMetricServiceCounter() {	
		
		this.continentCounter=this.meterRegistry.counter("continent.viewed");
		this.countryFlagCounter=this.meterRegistry.counter("country.flag.viewed");
	}
	
	public void incrementContinentCounter() {
		this.continentCounter.increment();
	}
	
	public void incrementCountryFlagCounter() {
		this.countryFlagCounter.increment();		
	}	
	
	
	public void saveORUpdateFlagPickerMetric(String type) {		
		
		Optional<FlagPicker> fagPicker=repository.findByType(type);
		
		Counter counter=("continent".equalsIgnoreCase(type)?this.continentCounter:countryFlagCounter);
		
		if(!fagPicker.isPresent()) {			
			
			FlagPicker fagPickerObj=new FlagPicker();
			fagPickerObj.setType(type);
			fagPickerObj.setCount(counter.count());
			
			repository.save(fagPickerObj);
			
		}else {
			
			FlagPicker fagPickerObj=fagPicker.get();
			fagPickerObj.setCount(counter.count());
			
			repository.save(fagPickerObj);
		}
		
	}
}
