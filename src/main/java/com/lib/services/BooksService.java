package com.lib.services;

import java.util.List;

import com.lib.vo.BookVo;

public interface BooksService 
{
	public String addBook(BookVo vo);
	public BookVo getBookById(Integer id);
	public List<BookVo> getAllBooks();
	public String updateBook(BookVo vo);
	public String deleteBook(Integer id);
	public List<BookVo> getAvailableBook();
}
