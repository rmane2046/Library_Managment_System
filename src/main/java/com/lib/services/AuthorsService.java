package com.lib.services;

import java.util.List;
import java.util.Optional;

import com.lib.entity.AuthorEntity;
import com.lib.vo.AuthorBookVo;
import com.lib.vo.AuthorVo;

import jakarta.transaction.Transactional;

public interface AuthorsService 
{
	@Transactional
	public String addAuthorwithBook(AuthorBookVo vo);

	@Transactional
	public String addAuthorOnly(AuthorVo vo);
	
	public AuthorVo getAuthorById(Integer id);
	public List<AuthorVo> getAllAuthor();
	@Transactional
	public String updateAuthor(AuthorVo vo);
	@Transactional
	public String deleteAuthor(Integer id);
}
