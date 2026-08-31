package com.lib.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lib.services.BookService;
import com.lib.vo.BookVo;

@RestController
@RequestMapping("/Book")
public class BookController 
{
	private BookService bookService;
	
	public BookController(BookService bookService)
	{
		this.bookService = bookService;
	}
	
	@PutMapping("/addbook")
	public ResponseEntity<String> addBook(@RequestBody BookVo vo)
	{
		String result = bookService.addBook(vo);
		if(result.startsWith("Book Add Successfully"))
		{
			return new ResponseEntity<String>(result,HttpStatus.OK);
		}
		else
			return new ResponseEntity<String>(result,HttpStatus.NOT_FOUND);
		
	}

}
