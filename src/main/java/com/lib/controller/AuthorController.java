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

import com.lib.services.AuthorServices;
import com.lib.vo.AuthorBookVo;
import com.lib.vo.AuthorVo;

@RestController
@RequestMapping("/author")
public class AuthorController 
{
	private AuthorServices authorService;
	
	public AuthorController(AuthorServices authorService)
	{
		this.authorService = authorService;
	}
	
	@PostMapping("/add-author-only")
	public ResponseEntity<String> addAuthor(@RequestBody AuthorVo vo)
	{
		String result = authorService.addAuthorOnly(vo);
		return new ResponseEntity<String>(result, HttpStatus.CREATED);
	}
	
	@PostMapping("/add-author-with-book")
	public ResponseEntity<String> addAuthorBook(@RequestBody AuthorBookVo vo)
	{
		String result = authorService.addAuthorwithBook(vo);
		return new ResponseEntity<String>(result, HttpStatus.CREATED);
	}
	
	@GetMapping("/getbyid/{id}")
	public ResponseEntity<?> getAuthorById(@PathVariable Integer id)
	{
		AuthorVo vo = authorService.getAuthorById(id);		
		return new ResponseEntity<AuthorVo>(vo,HttpStatus.OK);	
	}
	
	@GetMapping("/get-all-author")
	public ResponseEntity<?> getAllAuthors()
	{
		List<AuthorVo> author = authorService.getAllAuthor(); 
		return new ResponseEntity<List<AuthorVo>>(author,HttpStatus.OK);
	}
	
	@PutMapping("/update-author")
	public ResponseEntity<String> updateAuthor(@RequestBody AuthorVo vo)
	{
		String result = authorService.updateAuthor(vo);
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete-author/{id}")
	public ResponseEntity<String> deleteAuthor(@PathVariable Integer id)
	{
		String result = authorService.deleteAuthor(id);
		return new ResponseEntity<String>(result,HttpStatus.OK);
	}
}
