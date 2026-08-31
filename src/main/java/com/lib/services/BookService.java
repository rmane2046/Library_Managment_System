package com.lib.services;

import java.util.Optional;
import org.springframework.web.bind.annotation.RestController;

import com.lib.entity.AuthorEntity;
import com.lib.entity.BookEntity;
import com.lib.repository.AuthorEntityRepository;
import com.lib.repository.BookEntityRepository;
import com.lib.vo.BookVo;

@RestController
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

}
