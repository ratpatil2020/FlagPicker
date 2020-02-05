package com.apple.service;

import com.apple.bo.ContinentDTO;
import com.apple.bo.CountryDTO;

public interface ContinentService {
	ContinentDTO[] getAllContinentsAndCountries();
	ContinentDTO getAllCountriesByContinent(String continent);
	CountryDTO getCountryFlag(String country);
}
