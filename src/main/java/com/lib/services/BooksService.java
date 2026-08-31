package com.lib.services;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import com.lib.vo.BookVo;

public interface BooksService 
{
	public String addBook(BookVo vo);
}
