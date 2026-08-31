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
		if(result.startsWith("Author is Saved Successfully"))
		{
			return new ResponseEntity<String>(result, HttpStatus.CREATED);
			
		}
		else
			return new ResponseEntity<String>(result, HttpStatus.BAD_REQUEST);
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
		if(vo != null)
		{
			return new ResponseEntity<AuthorVo>(vo,HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("Author Not Found With Id : "+id,HttpStatus.NOT_FOUND);
		}
	
	}
	
	@GetMapping("/get-all-author")
	public ResponseEntity<?> getAllAuthors()
	{
		List<AuthorVo> author = authorService.getAllAuthor();
		if(author.isEmpty())
		{
			return new ResponseEntity<String>("No Author Found",HttpStatus.NOT_FOUND);
		}
		else 
			return new ResponseEntity<List<AuthorVo>>(author,HttpStatus.OK);
	}
	
	@PutMapping("/update-author")
	public ResponseEntity<String> updateAuthor(@RequestBody AuthorVo vo)
	{
		String result = authorService.updateAuthor(vo);
		if(result != null)
		{
			return new ResponseEntity<String>(result, HttpStatus.OK);
		}
		else
			return new ResponseEntity<String>("Author Not Found With Id : "+vo.getAuthorId(), HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/delete-author/{id}")
	public ResponseEntity<String> deleteAuthor(@PathVariable Integer id)
	{
		String result = authorService.deleteAuthor(id);
		if(result != null)
		{
			return new ResponseEntity<String>(result,HttpStatus.OK);
		}
		else
			return new ResponseEntity<String>("Author Not Found With ID : "+id,HttpStatus.NOT_FOUND);
	}
}
