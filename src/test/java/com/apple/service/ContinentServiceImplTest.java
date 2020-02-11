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
import com.apple.service.ContinentService;
import com.apple.util.JsonParserUtil;

@SpringBootTest 
public class ContinentServiceImplTest {

	@Mock
	private ContinentService continentService;	
	
	@Autowired
	private JsonParserUtil jsonParserUtil;
	
	@Test
	void getAllContinentsAndCountries_Success(){		
		
		ContinentDTO[] expectedContinentDTOs=jsonParserUtil.getContinentDTOs();
		when(continentService.getAllContinentsAndCountries()).thenReturn(expectedContinentDTOs);
		ContinentDTO[] actualContinentDTOs=continentService.getAllContinentsAndCountries();
		
		assertNotNull(actualContinentDTOs);
		assertArrayEquals(expectedContinentDTOs, actualContinentDTOs);
		
		verify(continentService, times(1)).getAllContinentsAndCountries();
	    verifyNoMoreInteractions(continentService);

	}	
	
	@Test
	void getAllCountriesByContinent_Success() {
		
		ContinentDTO continentDTOsExpected=jsonParserUtil.getContinentDTOs()[2];
		when(continentService.getAllCountriesByContinent("Asia")).thenReturn(continentDTOsExpected);
		
		ContinentDTO actualContinentDTO=continentService.getAllCountriesByContinent("Asia");		
		assertNotNull(actualContinentDTO);
		assertEquals("Asia", actualContinentDTO.getContinent());
		
		verify(continentService, times(1)).getAllCountriesByContinent("Asia");
	    verifyNoMoreInteractions(continentService);
	}

	@Test
	void getCountryFlagByCountry_Success() {
		
		CountryDTO countryDTOExpected=jsonParserUtil.getContinentDTOs()[1].getCountries().get(0);
		when(continentService.getCountryFlag("USA")).thenReturn(countryDTOExpected);
		
		CountryDTO actualCountryDTO=continentService.getCountryFlag("USA");
		
		assertNotNull(countryDTOExpected);
		assertEquals("USA", actualCountryDTO.getName());
		
		verify(continentService, times(1)).getCountryFlag("USA");
	    verifyNoMoreInteractions(continentService);
		
	}	
}
