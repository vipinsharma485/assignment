package com.pramati.autocomplete.autocomplete_cities_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.pramati.autocomplete.autocomplete_cities_service.model.ErrorResponseVO;
import com.pramati.autocomplete.autocomplete_cities_service.model.ResponseVO;

/**
 * <p>
 * This class is responsible for handling the custom and generic exception that
 * can occur in services and generate
 * <code>ErrorResponseVO</code> json.
 * </p>
 *
 *
 */
@RestControllerAdvice
public class ExceptionController {
	
	/**
	 * Method to handle <code>InvalidPrameterException</code>
	 *
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(value = InvalidPrameterException.class)
	public ResponseEntity<ResponseVO<ErrorResponseVO>> handleInvalidPrameterException(InvalidPrameterException ex) {
		final ErrorResponseVO error = new ErrorResponseVO();
		error.setErrorCode("300");
		error.setErrorMessage(ex.getMessage());
		return new ResponseEntity<ResponseVO<ErrorResponseVO>>(new ResponseVO<ErrorResponseVO>("Error", error),
				HttpStatus.OK);
	}

	/**
	 * Method to handle <code>StartDoednotExist</code>
	 *
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(value = StartDoednotExist.class)
	public ResponseEntity<ResponseVO<ErrorResponseVO>> handleStartDoednotExistException(StartDoednotExist ex) {
		final ErrorResponseVO error = new ErrorResponseVO();
		error.setErrorCode("400");
		error.setErrorMessage(ex.getMessage());
		return new ResponseEntity<ResponseVO<ErrorResponseVO>>(new ResponseVO<ErrorResponseVO>("Error", error),
				HttpStatus.OK);
	}

	/**
	 * Method to handle <code>Exception</code>
	 *
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<ResponseVO<ErrorResponseVO>> handleException(Exception ex) {

		final ErrorResponseVO error = new ErrorResponseVO();
		error.setErrorCode("500");
		error.setErrorMessage("Something went wrong while processing your request");
		return new ResponseEntity<ResponseVO<ErrorResponseVO>>(new ResponseVO<ErrorResponseVO>("Error", error),
				HttpStatus.SERVICE_UNAVAILABLE);
	}

}
