package com.pramati.autocomplete.autocomplete_cities_service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pramati.autocomplete.autocomplete_cities_service.model.CityCollection;


/**
 * This class is the Service that will expose methods to get cities.
 * 
 */
@Service
public class SuggestCityServiceImpl implements SuggestCityService {

	@Autowired
	private CityCollection cityColection;
	
	/**
	 * Gets list of suggested cities.
	 * 
	 * @param prefix
	 * @param atmost
	 * @return
	 */
	@Override
	public List<String> getsuggestedCities(String prefix, int atmost ) {
		return cityColection.getSuggestedCities(prefix, atmost);

	}

}
