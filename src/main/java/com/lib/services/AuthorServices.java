package com.lib.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.lib.entity.AuthorEntity;
import com.lib.entity.BookEntity;
import com.lib.repository.AuthorEntityRepository;
import com.lib.repository.BookEntityRepository;
import com.lib.vo.AuthorBookVo;
import com.lib.vo.AuthorVo;
import com.lib.vo.BookVo;

@Service
public class AuthorServices implements AuthorsService
{
	
	private AuthorEntityRepository authorRepo;
	private BookEntityRepository bookRepo;
	public AuthorServices(AuthorEntityRepository authorRepo, BookEntityRepository bookRepo)
	{
		this.authorRepo = authorRepo;
		this.bookRepo = bookRepo;
	}
	
	@Override
	public String addAuthorOnly(AuthorVo vo) 
	{
			Optional<AuthorEntity> authorOpt = authorRepo.findByAuthorPhone(vo.getAuthorPhone());
			if(authorOpt.isEmpty()) {
				AuthorEntity author = new AuthorEntity();
				author.setAuthorName(vo.getAuthorName());
				author.setAuthorPhone(vo.getAuthorPhone());
				authorRepo.save(author);
				return "Author is Saved Successfully with ID:"+author.getAuthorId();
			}
			else
				return "Author is Already Present with Number : "+vo.getAuthorPhone();
			
	}

	@Override
	public String addAuthorwithBook(AuthorBookVo vo) 
	{
		AuthorEntity author = new AuthorEntity();
		author.setAuthorName(vo.getAuthorvo().getAuthorName());
		author.setAuthorPhone(vo.getAuthorvo().getAuthorPhone());
		BookEntity books = new BookEntity();
		books.setBookTitle(vo.getBookvo().getBookTitle());
		books.setGenre(vo.getBookvo().getGenre());
		books.setTotalCopies(vo.getBookvo().getTotalCopies());
		books.setAvailableCopies(vo.getBookvo().getAvailableCopies());
		books.setAuthor(author);
		List<BookEntity> book = new ArrayList<BookEntity>();
		book.add(books);
		author.setBook(book);
		return "Author and Book Saved Successfully with Book_ID : "+books.getBookId()+" and Author_ID : "+author.getAuthorId();
	}

	@Override
	public AuthorVo getAuthorById(Integer id) 
	{		
		Optional<AuthorEntity> author = authorRepo.findById(id);
		if(author.isPresent())
		{
			AuthorVo vo = new AuthorVo();
			AuthorEntity entity = author.get();
			vo.setAuthorId(entity.getAuthorId());
			vo.setAuthorName(entity.getAuthorName());
			vo.setAuthorPhone(entity.getAuthorPhone());
			List<BookVo> bookVos = new ArrayList<>();
	        for (BookEntity book : entity.getBook()) {
	            BookVo bookVo = new BookVo();
	            bookVo.setBookId(book.getBookId());
	            bookVo.setBookTitle(book.getBookTitle());
	            bookVos.add(bookVo);
	        }

	        vo.setBook(bookVos);
	        return vo;
		}
		
		return null;
	}

	@Override
	public List<AuthorVo> getAllAuthor() {
		List<AuthorEntity> authorEntity = authorRepo.findAll();
		List<AuthorVo> authorVo = new ArrayList<AuthorVo>();
		for (AuthorEntity entity : authorEntity) {

		    AuthorVo vo = new AuthorVo();

		    vo.setAuthorId(entity.getAuthorId());
		    vo.setAuthorName(entity.getAuthorName());
		    vo.setAuthorPhone(entity.getAuthorPhone());

		    List<BookVo> bookVos = new ArrayList<>();

		    for (BookEntity book : entity.getBook()) {

		        BookVo bookVo = new BookVo();

		        bookVo.setBookId(book.getBookId());
		        bookVo.setBookTitle(book.getBookTitle());

		        bookVos.add(bookVo);
		    }

		    vo.setBook(bookVos);

		    authorVo.add(vo);
		}

		return authorVo;
	}

	@Override
	public String updateAuthor(AuthorVo vo) {
		Optional<AuthorEntity> entity = authorRepo.findById(vo.getAuthorId());
		if(entity.isPresent())
		{
			AuthorEntity author = entity.get();
			author.setAuthorName(vo.getAuthorName());
			author.setAuthorPhone(vo.getAuthorPhone());
			authorRepo.save(author);
			return "Author : "+vo.getAuthorId()+" Update Successfully";
		}
		else
			return null;
	}

	@Override
	public String deleteAuthor(Integer id) 
	{
			Optional<AuthorEntity> author = authorRepo.findById(id);
			if(author.isPresent())
			{
				List<BookEntity> books = bookRepo.findByAuthorAuthorId(id);

				for (BookEntity book : books) {
				    book.setAuthor(null);
				}

				bookRepo.saveAll(books);
				authorRepo.deleteById(id);
				return "Author "+id+" Deleted Successfully.";
			}
		return null;
	}

}
