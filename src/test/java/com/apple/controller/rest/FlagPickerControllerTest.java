package com.apple.controller.rest;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.apple.FlagFickerApplication;
import com.fasterxml.jackson.core.JsonProcessingException;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment=WebEnvironment.MOCK, classes={ FlagFickerApplication.class}) 
public class FlagPickerControllerTest {

	@Autowired
	private MockMvc mockMvc;  
	
	/**************************************************
	 **              Valid scenarios                 **                     
	****************************************************/
	@Test
	void getAllContinentsAndCountries_thenReturnsStatusOK() throws JsonProcessingException, Exception {
		
		 mockMvc.perform(get("/flagPicker/continents"))
				 .andExpect(content().contentType(MediaType.APPLICATION_JSON))
				 .andExpect(status().isOk())				               
				 .andExpect(jsonPath("$", hasSize(5)))
				 .andExpect(jsonPath("$[4].continent", is("Oceania")))
				 .andExpect(jsonPath("$[4].countries[0].name", is("Australia")));
	}
	
	@Test
	void getAllCountriesByContinent_whenValidInput_thenReturnsStatusOK() throws JsonProcessingException, Exception{
		
		mockMvc.perform(get("/flagPicker/continents/{continent}/countries","Asia"))
        		.andExpect(content().contentType(MediaType.APPLICATION_JSON))
        		.andExpect(status().isOk())	
        		.andExpect(jsonPath("$.continent", is("Asia")))
        		.andExpect(jsonPath("$.countries", hasSize(5)))        
        		.andExpect(jsonPath("$.countries[1].name", is("India")));
	}
	
	
	@Test
	void getCountryFlagByCountry_whenValidInput_thenReturnsStatusOK() throws JsonProcessingException, Exception{
		
		mockMvc.perform(get("/flagPicker/continents/{country}/flag","India"))
        	   .andExpect(content().contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())	        
               .andExpect(jsonPath("$.name", is("India")))
               .andExpect(jsonPath("$.flag", is("IN")));
	}
	
	/***************************************************
	 **              InValid scenarios               **                     
	****************************************************/
	@Test
	void getAllCountriesByContinent_whenInvalidInput_thenReturnsStatusBadRequest() throws JsonProcessingException, Exception{
		
		MvcResult result =mockMvc.perform(get("/flagPicker/continents/{continent}/countries","Antarctica"))
								 .andExpect(status().isBadRequest()).andReturn();
		
		String message=result.getResponse().getContentAsString();		
		assertEquals("Continent Not Found.", message, "Continent Not Found.");
		
	}
	
	@Test
	void getCountryFlagByCountry_whenInvalidInput_thenReturnsStatusBadRequest() throws JsonProcessingException, Exception{
		
		MvcResult result =mockMvc.perform(get("/flagPicker/continents/{country}/flag","Sri Lanka"))
								  .andExpect(status().isBadRequest()).andReturn();
		
		String message=result.getResponse().getContentAsString();		
		assertEquals("Country Not Found.", message, "Country Not Found.");
		
	}
}