package com.example.project2.rest;

import java.util.List;

import javax.persistence.NoResultException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.project2.dto.ResponseDTO;

@RestControllerAdvice(basePackages = "com.example.project2.rest")
public class RestExceptionController {
	
		//log org.slf4j.Logger
	    // log level
		Logger logger = LoggerFactory.getLogger(this.getClass());
		
		
		// bat cac loai exception tai day 
		
		@ExceptionHandler({NoResultException.class})
		@ResponseStatus(code = HttpStatus.NOT_FOUND)
		public ResponseDTO<Void> noResult(NoResultException ex) {
			logger.info("sql ex:", ex);
			return ResponseDTO.<Void>builder().status(404).error("Not Found").build();
		}
		
		@ExceptionHandler({MethodArgumentNotValidException.class})
		@ResponseStatus(code = HttpStatus.BAD_REQUEST)
		public ResponseDTO<Void> badInput(MethodArgumentNotValidException ex) {
			List<ObjectError> errors = ex.getBindingResult().getAllErrors();
			
			String msg ="";
			for(ObjectError e : errors) { 
				msg += e.getDefaultMessage()+";";
			}
			return ResponseDTO.<Void>builder().status(400).error(msg).build();
		}
			
		@ExceptionHandler({Exception.class})
		@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
		public ResponseDTO<Void> exception(Exception ex) {
			//ex.printStackTrace();
			logger.error("ex:", ex);
			return ResponseDTO.<Void>builder().status(500).error("SERVER_ERROR").build();
		}
}
