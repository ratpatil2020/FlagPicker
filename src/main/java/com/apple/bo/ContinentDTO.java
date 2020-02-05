package com.apple.bo;

import java.util.ArrayList;
import java.util.List;
import io.swagger.annotations.ApiModelProperty;

public class ContinentDTO {

	@ApiModelProperty(notes = "Hold the continent name", required = true)
	private String continent;
	
	@ApiModelProperty(notes = "Hold the countries and correposning flag associated with continent.")
	private List<CountryDTO> countries=new ArrayList<CountryDTO>();
	
	public String getContinent() {
		return continent;
	}
	public void setContinent(String continent) {
		this.continent = continent;
	}
	public List<CountryDTO> getCountries() {
		return countries;
	}
	public void setCountries(List<CountryDTO> countries) {
		this.countries = countries;
	}
	
	@Override
	public String toString() {
		return "ContinentDTO [continent=" + continent + ", countries=" + countries + "]";
	}
}
