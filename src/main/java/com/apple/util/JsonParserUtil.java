package com.apple.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.apple.bo.ContinentDTO;
import com.apple.exception.JsonParsingException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class JsonParserUtil {

	@Autowired
	private ObjectMapper mapper;
	
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
						"\"flag\": \"US\""+
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
