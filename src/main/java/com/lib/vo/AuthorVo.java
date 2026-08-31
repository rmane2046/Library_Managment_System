package com.lib.vo;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
public class AuthorVo 
{
	private Integer authorId;
	private String authorName;
	private long authorPhone;
	
	private List<BookVo> book;

}
