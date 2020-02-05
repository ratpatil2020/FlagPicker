package com.apple.service;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.apple.FlagFickerApplication;
import com.apple.bo.ContinentDTO;
import com.apple.bo.CountryDTO;
import com.apple.exception.JsonParsingException;
import com.apple.service.ContinentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment=WebEnvironment.MOCK, classes={ FlagFickerApplication.class}) 
public class ContinentServiceImplTest {

	@Mock
	private ContinentService continentService;	
	
	@Autowired
	private ObjectMapper mapper;	
	
	@Test
	void getAllContinentsAndCountries_Success(){		
		
		ContinentDTO[] expectedContinentDTOs=getContinentDTOs();
		when(continentService.getAllContinentsAndCountries()).thenReturn(expectedContinentDTOs);
		ContinentDTO[] actualContinentDTOs=continentService.getAllContinentsAndCountries();
		
		assertNotNull(actualContinentDTOs);
		assertArrayEquals(expectedContinentDTOs, actualContinentDTOs);
		
		verify(continentService, times(1)).getAllContinentsAndCountries();
	    verifyNoMoreInteractions(continentService);

	}	
	
	@Test
	void getAllCountriesByContinent_Success() {
		
		ContinentDTO continentDTOsExpected=getContinentDTOs()[2];
		when(continentService.getAllCountriesByContinent("Asia")).thenReturn(continentDTOsExpected);
		
		ContinentDTO actualContinentDTO=continentService.getAllCountriesByContinent("Asia");		
		assertNotNull(actualContinentDTO);
		assertEquals("Asia", actualContinentDTO.getContinent());
		
		verify(continentService, times(1)).getAllCountriesByContinent("Asia");
	    verifyNoMoreInteractions(continentService);
	}

	@Test
	void getCountryFlagByCountry_Success() {
		
		CountryDTO countryDTOExpected=getContinentDTOs()[1].getCountries().get(0);
		when(continentService.getCountryFlag("USA")).thenReturn(countryDTOExpected);
		
		CountryDTO actualCountryDTO=continentService.getCountryFlag("USA");
		
		assertNotNull(countryDTOExpected);
		assertEquals("USA", actualCountryDTO.getName());
		
		verify(continentService, times(1)).getCountryFlag("USA");
	    verifyNoMoreInteractions(continentService);
		
	}
	public ContinentDTO[] getContinentDTOs() {
		
		ContinentDTO[] continentDTOs=null;
		try {
			continentDTOs = mapper.readValue(getContinentJSON(), ContinentDTO[].class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
			throw new JsonParsingException("JsonMappingException : "+e.getMessage());
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			throw new JsonParsingException("JsonProcessingException : "+e.getMessage());
		}
		
		return continentDTOs;
	}
	
	private String getContinentJSON() {
		
		String continentJSON="["+
				"{"+
				"\"continent\": \"Africa\","+
				"\"countries\": ["+
					"{"+
						"\"name\": \"nigeria\","+
						"\"flag\": \"NG\""+
					"},"+
					"{"+
						"\"name\": \"Ethiopia\","+
						"\"flag\": \"ET\""+
					"},"+
					"{"+
						"\"name\": \"Egypt\","+
						"\"flag\": \"EG\""+
					"},"+
					"{"+
						"\"name\": \"DR Congo\","+
						"\"flag\": \"CO\""+
					"},"+
					"{"+
						"\"name\": \"South Africa\","+
						"\"flag\": \"ZA\""+
					"}"+
				"]"+
			"},"+
			"{"+
				"\"continent\": \"America\","+
				"\"countries\": ["+
					"{"+
						"\"name\": \"USA\","+
						"\"flag\": \"+US\""+
					"},"+
					"{"+
						"\"name\": \"Brazil\","+
						"\"flag\": \"BR\""+
					"},"+
					"{"+
						"\"name\": \"Mexico\","+
						"\"flag\": \"MX\""+
					"},"+
					"{"+
						"\"name\": \"Colombia\","+
						"\"flag\": \"CO\""+
					"},"+
					"{"+
						"\"name\": \"Argentina\","+
						"\"flag\": \"AR\""+
					"}"+
				"]"+
			"},"+
			"{"+
				"\"continent\": \"Asia\","+
				"\"countries\": ["+
					"{"+
						"\"name\": \"China\","+
						"\"flag\": \"CN\""+
					"},"+
					"{"+
						"\"name\": \"India\","+
						"\"flag\": \"IN\""+
					"},"+
					"{"+
						"\"name\": \"Indonesia\","+
						"\"flag\": \"ID\""+
					"},"+
					"{"+
						"\"name\": \"Pakistan\","+
						"\"flag\": \"PK\""+
					"},"+
					"{"+
						"\"name\": \"Bangladesh\","+
						"\"flag\": \"BD\""+
					"}"+
				"]"+
			"},"+
			"{"+
				"\"continent\": \"Europe\","+
				"\"countries\": ["+
					"{"+
						"\"name\": \"Russia\","+
						"\"flag\": \"RU\""+
					"},"+
					"{"+
						"\"name\": \"Germany\","+
						"\"flag\": \"DE\""+
					"},"+
					"{"+
						"\"name\": \"UK\","+
						"\"flag\": \"GB\""+
					"},"+
					"{"+
						"\"name\": \"france\","+
						"\"flag\": \"FR\""+
					"},"+
					"{"+
						"\"name\": \"Italy\","+
						"\"flag\": \"IT\""+
					"}"+
				"]"+
			"},"+
			"{"+
				"\"continent\": \"Oceania\","+
				"\"countries\": ["+
					"{"+
						"\"name\": \"Australia\","+
						"\"flag\": \"AU\""+
					"},"+
					"{"+
						"\"name\": \"Papua New Guinea\","+
						"\"flag\": \"PG\""+
					"},"+
					"{"+
						"\"name\": \"new Zealand\","+
						"\"flag\": \"NZ\""+
					"},"+
					"{"+
						"\"name\": \"fiji\","+
						"\"flag\": \"FJ\""+
					"},"+
					"{"+
						"\"name\": \"Solomon Islands\","+
						"\"flag\": \"SB\""+
					"}"+
				"]"+
			"}"+
		"]";

	      return continentJSON;
	   }
}
