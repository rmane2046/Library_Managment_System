package com.lib.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.lib.entity.AuthorEntity;
import com.lib.entity.BookEntity;
import com.lib.repository.AuthorEntityRepository;
import com.lib.repository.BookEntityRepository;
import com.lib.vo.BookVo;

@Service
public class BookService implements BooksService
{
	private BookEntityRepository bookRepo;
	private AuthorEntityRepository authorRepo;
	
	public BookService(BookEntityRepository bookRepo, AuthorEntityRepository authorRepo)
	{
		this.bookRepo = bookRepo;
		this.authorRepo = authorRepo;
	}
	
	@Override
	public String addBook(BookVo vo) 
	{
		Optional<AuthorEntity> authorEntity = authorRepo.findById(vo.getAuthorId());
		Optional<BookEntity> book = bookRepo.findByBookTitle(vo.getBookTitle());
		if(authorEntity.isPresent() && book.isEmpty())
		{		
			BookEntity bookEntity = new BookEntity();
			bookEntity.setBookTitle(vo.getBookTitle());
			bookEntity.setGenre(vo.getGenre());
			bookEntity.setTotalCopies(vo.getTotalCopies());
			bookEntity.setAvailableCopies(vo.getAvailableCopies());
			bookEntity.setAuthor(authorEntity.get());
			bookRepo.save(bookEntity);
			return "Book Add Successfully with ID :"+bookEntity.getBookId();
		}
		else
			return "Author Not Found or Book Already Exists";
	}

	@Override
	public BookVo getBookById(Integer id)
	{
		Optional<BookEntity> entity = bookRepo.findById(id);
		if(entity.isPresent())
		{
			BookEntity bookEntity = entity.get();
			BookVo vo = new BookVo();
			vo.setBookId(bookEntity.getBookId());
			vo.setBookTitle(bookEntity.getBookTitle());
			vo.setGenre(bookEntity.getGenre());
			vo.setAvailableCopies(bookEntity.getAvailableCopies());
			vo.setTotalCopies(bookEntity.getTotalCopies());
			vo.setAuthorId(bookEntity.getAuthor().getAuthorId());
			return vo;
		}
		return null;
	}

	@Override
	public List<BookVo> getAllBooks()
	{
		List<BookEntity> entity = bookRepo.findAll();
		List<BookVo> vo = new ArrayList<BookVo>();
		for(BookEntity enti : entity)
		{
			BookVo vo1 = new BookVo();
			vo1.setBookId(enti.getBookId());
			vo1.setBookTitle(enti.getBookTitle());
			vo1.setGenre(enti.getGenre());
			vo1.setAvailableCopies(enti.getAvailableCopies());
			vo1.setTotalCopies(enti.getTotalCopies());
			vo1.setAuthorId(enti.getAuthor().getAuthorId());
			vo.add(vo1);
		}
		return vo;
	}

	@Override
	public String updateBook(BookVo vo) {
		Optional<BookEntity> book = bookRepo.findById(vo.getBookId());
		if(book.isPresent())
		{
			BookEntity entity = book.get();
			entity.setBookTitle(vo.getBookTitle());
			entity.setGenre(vo.getGenre());
			entity.setAvailableCopies(vo.getAvailableCopies());
			entity.setTotalCopies(vo.getTotalCopies());
			bookRepo.save(entity);
			return "Book ID : "+vo.getBookId()+" Updated Successfully.";
		}
		return "Book Not Found with ID : "+vo.getBookId();
	}

	@Override
	public String deleteBook(Integer id) 
	{
		Optional<BookEntity> book = bookRepo.findById(id);	
		if(book.isPresent())
		{
			bookRepo.deleteById(id);
			return "Book Delete With ID : "+id;
		}
		else 
			return "Book Not Found With ID :"+id;
					
	}

	@Override
	public List<BookVo> getAvailableBook() {
		List<BookEntity> entity = bookRepo.findAvailable();
		List<BookVo> vo = new ArrayList<BookVo>();
		for(BookEntity book : entity)
		{
			BookVo vo1 = new BookVo();
			vo1.setBookId(book.getBookId());
			vo1.setBookTitle(book.getBookTitle());
			vo1.setGenre(book.getGenre());
			vo1.setAvailableCopies(book.getAvailableCopies());
			vo1.setTotalCopies(book.getTotalCopies());
			vo1.setAuthorId(book.getAuthor().getAuthorId());
			vo.add(vo1);
		}
		return vo;
	}
	

}
