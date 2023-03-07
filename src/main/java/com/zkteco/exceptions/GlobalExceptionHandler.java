package com.zkteco.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import com.zkteco.entity.MyErrorDetails;

@ControllerAdvice
@ResponseStatus
public class GlobalExceptionHandler {

	@ExceptionHandler(DepartmentNotFoundException.class)
	public ResponseEntity<MyErrorDetails> departmentNotFoundHandler(DepartmentNotFoundException dnf, WebRequest req){
		MyErrorDetails err = new MyErrorDetails(LocalDateTime.now(),dnf.getMessage(),req.getDescription(false));
		return new ResponseEntity<MyErrorDetails>(err,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(DataNotFoundException.class)
	public ResponseEntity<MyErrorDetails> dataNotFoundHandler(DataNotFoundException dnf, WebRequest req){
		MyErrorDetails err = new MyErrorDetails(LocalDateTime.now(),dnf.getMessage(),req.getDescription(false));
		return new ResponseEntity<MyErrorDetails>(err,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<MyErrorDetails> otherExceptionHandler(Exception dnf, WebRequest req){
		MyErrorDetails err = new MyErrorDetails(LocalDateTime.now(),dnf.getMessage(),req.getDescription(false));
		return new ResponseEntity<MyErrorDetails>(err,HttpStatus.NOT_FOUND);
	}
	
}
