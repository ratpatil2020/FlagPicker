package com.apple.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.apple.bo.ContinentDTO;
import com.apple.bo.CountryDTO;
import com.apple.exception.ContinentNotFoundException;
import com.apple.exception.CountryNotFoundException;
import com.apple.util.JsonParserUtil;

@Service
public class ContinentServiceImpl implements ContinentService{	
		 
	@Autowired
	private JsonParserUtil jsonParser;
	
	public ContinentDTO[] getAllContinentsAndCountries() {
		ContinentDTO[] continentDTOs=jsonParser.getContinentDTOs();	
		
		return continentDTOs;
	}
	
	public ContinentDTO getAllCountriesByContinent(String continent) {	
		
		ContinentDTO[] continentDTOs=jsonParser.getContinentDTOs();		
		
		List<ContinentDTO> continentDTOList = Arrays.asList(continentDTOs);		
		Optional<ContinentDTO> continentObj=continentDTOList.stream()				                                 
				                                  .filter(continentDTO -> continentDTO.getContinent().equalsIgnoreCase(continent))
				                                  .limit(1)
				                                  .findFirst();		                             
		if(!continentObj.isPresent())  
		        throw new  ContinentNotFoundException("Continent Not Found.");	
		
		return continentObj.get();
	}	
    
	
	public CountryDTO getCountryFlag(String country) {
						
		ContinentDTO[] continentDTOs=jsonParser.getContinentDTOs();
		List<ContinentDTO> continentDTOList = Arrays.asList(continentDTOs);		
		Optional<CountryDTO> countryObj=continentDTOList.stream()				
														.flatMap(continentDTO->continentDTO.getCountries().stream())
														.filter(countryDTO->countryDTO.getName().equalsIgnoreCase(country))
														.findFirst();
		      
		if(!countryObj.isPresent())
			throw new CountryNotFoundException("Country Not Found.");        
		
		return countryObj.get();
	}	
}
