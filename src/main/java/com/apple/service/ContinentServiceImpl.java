package com.apple.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.apple.bo.ContinentDTO;
import com.apple.bo.CountryDTO;
import com.apple.exception.ContinentNotFoundException;
import com.apple.exception.CountryNotFoundException;
import com.apple.util.JsonParserUtil;


@Service
public class ContinentServiceImpl implements ContinentService{
	
	 private static final Logger LOGGER = LoggerFactory.getLogger(ContinentServiceImpl.class);
	 
	@Autowired
	private JsonParserUtil jsonParser;
	
	
	
	public ContinentDTO[] getAllContinentsAndCountries() {
	    String method="getAllContinentsAndCountries() : ";
		LOGGER.info(method+" starting ... ");
		ContinentDTO[] continentDTOs=jsonParser.getContinentDTOs();	
		
		LOGGER.info(method+" ContinentDTOs->{}",continentDTOs.toString());
		
		return continentDTOs;
	}
	
	public ContinentDTO getAllCountriesByContinent(String continent) {
		
		String method="getAllCountriesByContinent() : ";
		LOGGER.info(method+" starting ... ");
		
		ContinentDTO[] continentDTOs=jsonParser.getContinentDTOs();		
		
		List<ContinentDTO> continentDTOList = Arrays.asList(continentDTOs);		
		Optional<ContinentDTO> continentObj=continentDTOList.stream()				                                 
				                                  .filter(continentDTO -> continentDTO.getContinent().equalsIgnoreCase(continent))
				                                  .limit(1)
				                                  .findFirst();		                             
		if(!continentObj.isPresent())  
		        throw new  ContinentNotFoundException("Continent Not Found.");				                                  
		
		LOGGER.info(method+" ContinentDTO->{}",continentObj.get());
		
		return continentObj.get();
	}	
    
	
	public CountryDTO getCountryFlag(String country) {
		
		String method="getCountryFlag() : ";
		LOGGER.info(method+" starting ... ");
		
		ContinentDTO[] continentDTOs=jsonParser.getContinentDTOs();
		List<ContinentDTO> continentDTOList = Arrays.asList(continentDTOs);		
		Optional<CountryDTO> countryObj=continentDTOList.stream()				
														.flatMap(continentDTO->continentDTO.getCountries().stream())
														.filter(countryDTO->countryDTO.getName().equalsIgnoreCase(country))
														.findFirst();
		      
		if(!countryObj.isPresent())
			throw new CountryNotFoundException("Country Not Found.");
        
		LOGGER.info(method+" CountryDTO->{}",countryObj.get());
		return countryObj.get();
	}
	
	
}
