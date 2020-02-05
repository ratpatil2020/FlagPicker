package com.apple.bo;

import java.util.ArrayList;
import java.util.List;

public class ContinentDTO {

	private String continent;
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
