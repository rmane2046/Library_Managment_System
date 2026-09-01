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
		if(result.startsWith("Book Add Successfully"))
		{
			return new ResponseEntity<String>(result,HttpStatus.OK);
		}
		else
			return new ResponseEntity<String>(result,HttpStatus.NOT_FOUND);
		
	}
	
	@GetMapping("/get-book/{id}")
	public ResponseEntity<?> getBookById(@PathVariable Integer id)
	{
		BookVo vo = bookService.getBookById(id);
		if(vo != null)
		{
			return new ResponseEntity<BookVo>(vo,HttpStatus.OK);
		}
		else
			return new ResponseEntity<String>("Book Not Found with ID : "+id,HttpStatus.NOT_FOUND);
		
	}
	
	@GetMapping("/find-all-books")
	public ResponseEntity<?> getAllBooks()
	{
		List<BookVo> vo = bookService.getAllBooks();
		if(vo.isEmpty())
		{
			return new ResponseEntity<String>("No Book Found",HttpStatus.NOT_FOUND);
		}
		else
			return new ResponseEntity<List<BookVo>>(vo,HttpStatus.OK);
	}
	
	@PutMapping("/update-book")
	public ResponseEntity<String> updateBook(@RequestBody BookVo vo)
	{
		String result = bookService.updateBook(vo);
		if(result.contains("Not Found "))
		{
			return new ResponseEntity<String>(result,HttpStatus.NOT_FOUND);
		}
		else 
			return new ResponseEntity<String>(result,HttpStatus.OK);
	}
	
	@DeleteMapping("/delete-book/{id}")
	public ResponseEntity<String> deleteBook(@PathVariable Integer id)
	{
		String result = bookService.deleteBook(id);
		if(result.contains("Not Found"))
		{
			return new ResponseEntity<String>(result,HttpStatus.NOT_FOUND);
		}
		else
			return new ResponseEntity<String>(result,HttpStatus.OK);
	}
	
	@GetMapping("/get-available-book")
	public ResponseEntity<?> getAvailableBooks()
	{
		List<BookVo> vo = bookService.getAvailableBook();
		if(vo.isEmpty())
		{
			return new ResponseEntity<String>("No Books Are Available",HttpStatus.NOT_FOUND);
		}
		else
			return new ResponseEntity<List<BookVo>>(vo,HttpStatus.OK);
	}
	

}
