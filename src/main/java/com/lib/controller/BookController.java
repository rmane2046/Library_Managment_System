package com.lib.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	@PostMapping("/addbook")
	public ResponseEntity<String> addBook(@RequestBody BookVo vo)
	{
		String result = bookService.addBook(vo);
		return new ResponseEntity<String>(result,HttpStatus.OK);		
	}
	
	@GetMapping("/get-book/{id}")
	public ResponseEntity<?> getBookById(@PathVariable Integer id)
	{
		BookVo vo = bookService.getBookById(id);
		return new ResponseEntity<BookVo>(vo,HttpStatus.OK);		
	}
	
	@GetMapping("/find-all-books")
	public ResponseEntity<?> getAllBooks()
	{
		List<BookVo> vo = bookService.getAllBooks();
		return new ResponseEntity<List<BookVo>>(vo,HttpStatus.OK);
	}
	
	@PutMapping("/update-book")
	public ResponseEntity<String> updateBook(@RequestBody BookVo vo)
	{
		String result = bookService.updateBook(vo);
		return new ResponseEntity<String>(result,HttpStatus.OK);
	}
	
	@DeleteMapping("/delete-book/{id}")
	public ResponseEntity<String> deleteBook(@PathVariable Integer id)
	{
		String result = bookService.deleteBook(id);
		return new ResponseEntity<String>(result,HttpStatus.OK);
	}
	
	@GetMapping("/get-available-book")
	public ResponseEntity<?> getAvailableBooks()
	{
		List<BookVo> vo = bookService.getAvailableBook();
		return new ResponseEntity<List<BookVo>>(vo,HttpStatus.OK);
	}
	

}
