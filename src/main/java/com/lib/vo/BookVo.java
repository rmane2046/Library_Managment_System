package com.lib.vo;


import org.springframework.stereotype.Component;
import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
public class BookVo 
{
	private Integer bookId;
	private String bookTitle;
	private String genre;
	private int totalCopies;
	private int availableCopies;
	
	private Integer authorId;
	private Integer issueRecordId;
}
