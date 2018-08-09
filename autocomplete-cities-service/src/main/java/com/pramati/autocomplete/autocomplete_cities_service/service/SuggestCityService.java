package com.pramati.autocomplete.autocomplete_cities_service.service;


import java.util.List;

public interface SuggestCityService {

	public List<String> getsuggestedCities(String prefix, int atmost ) ;
}
