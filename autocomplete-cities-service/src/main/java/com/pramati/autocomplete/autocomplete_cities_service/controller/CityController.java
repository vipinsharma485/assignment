package com.pramati.autocomplete.autocomplete_cities_service.controller;

import java.util.List;

import javax.activity.InvalidActivityException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pramati.autocomplete.autocomplete_cities_service.exception.InvalidPrameterException;
import com.pramati.autocomplete.autocomplete_cities_service.model.ResponseVO;
import com.pramati.autocomplete.autocomplete_cities_service.service.SuggestCityService;

/**
 * This class is the Rest Controller that will expose methods for suggesting city names. 
 * 
 * @author vipsharm5
 *
 */
@RestController
public class CityController {

	@Autowired private SuggestCityService service;
	
	/**
	 * This method accepts start and atmost as request parameter and provide suggested city names.
	 * @param httpServletRequest
	 * @return
	 * @throws InvalidActivityException
	 */
	@GetMapping(value = "/suggest_cities")
	public ResponseEntity<ResponseVO<List<String>>>  suggestCities(HttpServletRequest httpServletRequest) throws InvalidActivityException{
		String start = httpServletRequest.getParameter("start");
		String atmost = httpServletRequest.getParameter("atmost");
		int limit;
		if(StringUtils.isEmpty(start)|| StringUtils.isEmpty(atmost)){
			throw new InvalidPrameterException("Parameter start and atmost are mendatory.");
		}
		try {			
		 limit = Integer.parseInt(atmost);
			
		} catch (Exception e) {
			throw new InvalidPrameterException("Parameter atmost should be a valid number.");
		}
		List<String> list = service.getsuggestedCities(start, limit);
		ResponseVO<List<String>> response = new ResponseVO<>();
		response.setResult(list);
		response.setStatus("OK");
		return new ResponseEntity<ResponseVO<List<String>>>(response,  HttpStatus.OK);
	}
}
