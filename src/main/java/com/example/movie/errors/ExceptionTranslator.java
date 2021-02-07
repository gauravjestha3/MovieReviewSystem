package com.example.movie.errors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;


@ControllerAdvice
public class ExceptionTranslator {
	private final Logger log = LoggerFactory.getLogger(ExceptionTranslator.class);

	@ExceptionHandler(CustomParameterizedException.class)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ParameterizedErrorVM processParameterizedValidationError(CustomParameterizedException ex) {
		return ex.getErrorVM();
	}

	protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex,
			final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
		log.info("--------Some Fields/Params Are Missing in request body -------------");
		log.info("Exception occered while serving Request: ", ex);
		BindingResult result = ex.getBindingResult();
		List<org.springframework.validation.FieldError> fieldErrors = result.getFieldErrors();
		Map<String, Object> map = new HashMap<String, Object>();
		List<CustomFieldErrors> customFieldErrorsList = new ArrayList<CustomFieldErrors>();
		for (FieldError fieldError : fieldErrors) {
			CustomFieldErrors customError = new CustomFieldErrors();
			customError.setFieldName(fieldError.getField());
			customError.setMessage(fieldError.getDefaultMessage());
			customFieldErrorsList.add(customError);
		}
		map.put("success", Boolean.FALSE);
		map.put("fieldErrors", customFieldErrorsList);
		return new ResponseEntity<>(map, headers, HttpStatus.OK);
		// return handleExceptionInternal(ex, fieldErrors,
		// headers,HttpStatus.BAD_REQUEST, request);
	}


}
