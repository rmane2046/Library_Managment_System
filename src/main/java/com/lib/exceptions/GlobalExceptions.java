package com.lib.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptions
{	
	@ExceptionHandler(MemberAlreadyExitException.class)
	public ResponseEntity<ExceptionDetails> memberAlredyExists(MemberAlreadyExitException ex)
	{
		ExceptionDetails details = new ExceptionDetails(LocalDateTime.now(),ex.getMessage(),"409 Already Exists");
		return new ResponseEntity<ExceptionDetails>(details,HttpStatus.CONFLICT);		
	}
	
	@ExceptionHandler(MemberNotFoundException.class)
	public ResponseEntity<ExceptionDetails> memberNotFound(MemberNotFoundException ex)
	{
		ExceptionDetails details = new ExceptionDetails(LocalDateTime.now(),ex.getMessage(),"404 Not Found");
		return new ResponseEntity<ExceptionDetails>(details,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(BookNotFoundException.class)
	public ResponseEntity<ExceptionDetails> bookNotFound(BookNotFoundException ex)
	{
		ExceptionDetails details = new ExceptionDetails(LocalDateTime.now(),ex.getMessage(),"404 Not Found");
		return new ResponseEntity<ExceptionDetails>(details,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(BookNotAvailableException.class)
	public ResponseEntity<ExceptionDetails> bookNotAvailable(BookNotAvailableException ex)
	{
		ExceptionDetails details = new ExceptionDetails(LocalDateTime.now(),ex.getMessage(),"400 Not Available in Stock");
		return new ResponseEntity<ExceptionDetails>(details,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(RecordNotFoundException.class)
	public ResponseEntity<ExceptionDetails> recordNotAvailable(RecordNotFoundException ex)
	{
		ExceptionDetails details = new ExceptionDetails(LocalDateTime.now(),ex.getMessage(),"404 Record Not Found");
		return new ResponseEntity<ExceptionDetails>(details,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(BookAlreadyReturnedException.class)
	public ResponseEntity<ExceptionDetails> bookReturnedAlready(BookAlreadyReturnedException ex)
	{
		ExceptionDetails details = new ExceptionDetails(LocalDateTime.now(),ex.getMessage(),"404 Book Not Found For Return");
		return new ResponseEntity<ExceptionDetails>(details,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(AuthorAlreadyPresentException.class)
	public ResponseEntity<ExceptionDetails> AuthorAlredyExists(AuthorAlreadyPresentException ex)
	{
		ExceptionDetails details = new ExceptionDetails(LocalDateTime.now(),ex.getMessage(),"409 Already Exists");
		return new ResponseEntity<ExceptionDetails>(details,HttpStatus.CONFLICT);		
	}
	
	@ExceptionHandler(AuthorNotFoundException.class)
	public ResponseEntity<ExceptionDetails> AuthorNotFound(AuthorNotFoundException ex)
	{
		ExceptionDetails details = new ExceptionDetails(LocalDateTime.now(),ex.getMessage(),"404 Not Found");
		return new ResponseEntity<ExceptionDetails>(details,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(BookAlreadyPresentException.class)
	public ResponseEntity<ExceptionDetails> bookAlredyExists(BookAlreadyPresentException ex)
	{
		ExceptionDetails details = new ExceptionDetails(LocalDateTime.now(),ex.getMessage(),"409 Already Exists");
		return new ResponseEntity<ExceptionDetails>(details,HttpStatus.CONFLICT);		
	}
	
}
