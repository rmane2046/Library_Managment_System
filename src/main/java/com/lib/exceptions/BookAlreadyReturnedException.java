package com.lib.exceptions;

public class BookAlreadyReturnedException extends RuntimeException
{
	public BookAlreadyReturnedException(String msg) 
	{
		super(msg);
	}

}
