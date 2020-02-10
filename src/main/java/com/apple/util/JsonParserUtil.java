package com.apple.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import com.apple.bo.ContinentDTO;
import com.apple.exception.ContinentJSONFileNotFound;
import com.apple.exception.JsonParsingException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class JsonParserUtil {

	@Autowired
	private ObjectMapper mapper;
	
	@Autowired
	private ResourceLoader resourceLoader;
	
	public ContinentDTO[] getContinentDTOs() {
		
		ContinentDTO[] continentDTOs=null;
		
		try {			
			continentDTOs = mapper.readValue(getContinentJSON(), ContinentDTO[].class);
		} catch (JsonMappingException e) {
			//e.printStackTrace();
			throw new JsonParsingException("JsonMappingException : "+e.getMessage());
		} catch (JsonProcessingException e) {
			//e.printStackTrace();
			throw new JsonParsingException("JsonProcessingException : "+e.getMessage());
		}catch(IOException e) {
			throw new JsonParsingException("IOException : "+e.getMessage());
		}
		
		return continentDTOs;
	}
	
	private String getContinentJSON() throws IOException {
		
		Resource resource=resourceLoader.getResource("classpath:continents.json");	
		File continentsJsonFile=resource.getFile();
		
		if(!continentsJsonFile.exists())
			throw new ContinentJSONFileNotFound();
		
		String continentsJson=new String(Files.readAllBytes(continentsJsonFile.toPath()));		
		
		return continentsJson;
	}	
}
